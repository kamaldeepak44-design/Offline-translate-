package com.example.domain.translation

import android.content.Context
import android.util.Log
import com.example.data.model.DictionaryEntry
import com.example.data.model.TamilSpeechStyle
import com.google.mlkit.common.model.DownloadConditions
import com.google.mlkit.common.model.RemoteModelManager
import com.google.mlkit.nl.translate.TranslateRemoteModel
import com.google.mlkit.nl.translate.Translation
import com.google.mlkit.nl.translate.Translator
import com.google.mlkit.nl.translate.TranslatorOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.io.File
import java.util.Locale
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.resume

object TamilTranslator {
    private const val TAG = "TamilTranslator"

    private val options = TranslatorOptions.Builder()
        .setSourceLanguage("en")
        .setTargetLanguage("ta")
        .build()

    private val translator: Translator by lazy {
        Translation.getClient(options)
    }

    private val modelManager: RemoteModelManager by lazy {
        RemoteModelManager.getInstance()
    }

    private val tamilModel: TranslateRemoteModel by lazy {
        TranslateRemoteModel.Builder("ta").build()
    }

    private val _modelStatus = MutableStateFlow(TranslationModelStatus.NOT_INSTALLED)
    val modelStatus: StateFlow<TranslationModelStatus> = _modelStatus.asStateFlow()

    @Volatile
    private var isDownloadedCache: Boolean? = null

    @Volatile
    private var appContext: Context? = null

    private val translationCache = ConcurrentHashMap<String, String>()

    private val KNOWN_CHARACTER_VOCATIVES = mapOf(
        "peter" to "பீட்டர்",
        "spiderman" to "ஸ்பைடர்மேன்",
        "spider-man" to "ஸ்பைடர்மேன்",
        "bruce" to "புரூஸ்",
        "batman" to "பேட்மேன்",
        "clark" to "கிளார்க்",
        "superman" to "சூப்பர்மேன்",
        "tony" to "டோனி",
        "ironman" to "அயர்ன்மேன்",
        "iron man" to "அயர்ன்மேன்",
        "steve" to "ஸ்டீவ்",
        "captain" to "கேப்டன்",
        "thor" to "தோர்",
        "loki" to "லோகி",
        "hulk" to "ஹல்க்",
        "naruto" to "நருடோ",
        "sasuke" to "சாசுகே",
        "sakura" to "சகுரா",
        "goku" to "கோகு",
        "vegeta" to "வெஜிடா",
        "luffy" to "லஃபி",
        "zoro" to "ஜோரோ",
        "nami" to "நமி",
        "sanji" to "சாஞ்சி",
        "ichigo" to "இச்சிகோ",
        "edward" to "எட்வர்ட்",
        "alphonse" to "அல்போன்ஸ்",
        "eren" to "ஏரன்",
        "mikasa" to "மிகாசா",
        "levi" to "லீவை",
        "arthur" to "ஆர்தர்",
        "john" to "ஜான்",
        "sarah" to "சாரா",
        "alex" to "அலெக்ஸ்",
        "jack" to "ஜாக்",
        "mary" to "மேரி",
        "dr. stone" to "டாக்டர் ஸ்டோன்",
        "sensei" to "சென்சி",
        "lord" to "பிரபு"
    )

    init {
        // Pre-load full English to Tamil translation pack into in-memory cache (Standard Tamil)
        for ((en, ta) in TamilDictionaryPack.EXTENSIVE_PHRASES) {
            putInCache(en, ta)
        }
    }

    fun initialize(context: Context) {
        appContext = context.applicationContext

        // Check model status via RemoteModelManager without prematurely loading native JNI translator
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val downloaded = isModelDownloaded()
                _modelStatus.value = if (downloaded) TranslationModelStatus.INSTALLED else TranslationModelStatus.NOT_INSTALLED
            } catch (e: Exception) {
                Log.d(TAG, "ML Kit download check: ${e.message}")
            }
        }
    }

    fun isModelPhysicallyPresent(context: Context? = appContext): Boolean {
        val ctx = context ?: return false
        return try {
            val candidateDirs = listOfNotNull(
                ctx.noBackupFilesDir?.let { File(it, "com.google.mlkit.translate.models/en_ta") },
                ctx.noBackupFilesDir?.let { File(it, "com.google.mlkit.translate.models") },
                ctx.filesDir?.let { File(it, "com.google.mlkit.translate.models/en_ta") },
                ctx.filesDir?.let { File(it, "com.google.mlkit.translate.models") }
            )
            for (dir in candidateDirs) {
                if (dir.exists()) {
                    val files = dir.listFiles() ?: continue
                    for (file in files) {
                        val name = file.name
                        if ((name.endsWith(".bin") || name.endsWith(".pb")) && file.length() > 1024) {
                            return true
                        }
                    }
                }
            }
            false
        } catch (e: Exception) {
            Log.w(TAG, "Error checking model files on disk", e)
            false
        }
    }

    fun getFromCache(english: String): String? {
        val key = normalizeKey(english)
        return translationCache[key]
    }

    fun putInCache(english: String, tamil: String) {
        if (english.isBlank() || tamil.isBlank()) return
        val key = normalizeKey(english)
        translationCache[key] = tamil.trim()
    }

    fun clearCache() {
        translationCache.clear()
    }

    fun getCacheSize(): Int = translationCache.size

    fun normalizeKey(text: String): String {
        return text.trim().lowercase(Locale.ROOT)
    }

    suspend fun checkModelStatus(): TranslationModelStatus = withContext(Dispatchers.IO) {
        val downloaded = isModelDownloaded()
        val status = if (downloaded) TranslationModelStatus.INSTALLED else TranslationModelStatus.NOT_INSTALLED
        _modelStatus.value = status
        status
    }

    suspend fun isModelDownloaded(): Boolean = withContext(Dispatchers.IO) {
        val cached = isDownloadedCache
        if (cached != null) return@withContext cached

        suspendCancellableCoroutine { continuation ->
            modelManager.isModelDownloaded(tamilModel)
                .addOnSuccessListener { downloaded ->
                    isDownloadedCache = downloaded
                    _modelStatus.value = if (downloaded) TranslationModelStatus.INSTALLED else TranslationModelStatus.NOT_INSTALLED
                    continuation.resume(downloaded)
                }
                .addOnFailureListener {
                    isDownloadedCache = false
                    continuation.resume(false)
                }
        }
    }

    suspend fun downloadModel(): Result<Unit> = withContext(Dispatchers.IO) {
        _modelStatus.value = TranslationModelStatus.DOWNLOADING
        suspendCancellableCoroutine { continuation ->
            try {
                val conditions = DownloadConditions.Builder().build()
                modelManager.download(tamilModel, conditions)
                    .addOnSuccessListener {
                        isDownloadedCache = true
                        _modelStatus.value = TranslationModelStatus.INSTALLED
                        continuation.resume(Result.success(Unit))
                    }
                    .addOnFailureListener { ex ->
                        isDownloadedCache = false
                        _modelStatus.value = TranslationModelStatus.ERROR
                        Log.e(TAG, "Failed to download Tamil translation model", ex)
                        continuation.resume(Result.failure(ex))
                    }
            } catch (e: Throwable) {
                isDownloadedCache = false
                _modelStatus.value = TranslationModelStatus.ERROR
                continuation.resume(Result.failure(e))
            }
        }
    }

    suspend fun deleteModel(): Result<Unit> = withContext(Dispatchers.IO) {
        suspendCancellableCoroutine { continuation ->
            modelManager.deleteDownloadedModel(tamilModel)
                .addOnSuccessListener {
                    isDownloadedCache = false
                    _modelStatus.value = TranslationModelStatus.NOT_INSTALLED
                    continuation.resume(Result.success(Unit))
                }
                .addOnFailureListener { ex ->
                    Log.e(TAG, "Failed to delete Tamil model", ex)
                    continuation.resume(Result.failure(ex))
                }
        }
    }

    private fun extractCharacterVocative(text: String): Pair<String?, String> {
        val commaIndex = text.indexOf(',')
        if (commaIndex > 0) {
            val potentialName = text.substring(0, commaIndex).trim().lowercase(Locale.ROOT)
            val tamilName = KNOWN_CHARACTER_VOCATIVES[potentialName]
            if (tamilName != null) {
                val remainder = text.substring(commaIndex + 1).trim()
                return Pair("$tamilName, ", remainder)
            }
        }
        return Pair(null, text)
    }

    suspend fun translateWithResult(
        englishText: String,
        customEntries: List<DictionaryEntry> = emptyList(),
        speechStyle: TamilSpeechStyle = TamilSpeechStyle.STANDARD,
        userTranslations: Map<String, String> = emptyMap(),
        previousDialogue: String? = null,
        speakerName: String? = null
    ): TranslationResult = withContext(Dispatchers.IO) {
        val trimmed = englishText.trim()
        if (trimmed.isEmpty()) {
            return@withContext TranslationResult("", isSuccess = true, source = TranslationSource.CACHE)
        }

        val normKey = normalizeKey(trimmed)

        // If spoken style is requested, check spoken phrases first
        if (speechStyle == TamilSpeechStyle.NATURAL_SPOKEN) {
            TamilDictionaryPack.SPOKEN_PHRASES[normKey]?.let { spoken ->
                return@withContext TranslationResult(spoken, isSuccess = true, source = TranslationSource.PHRASEBOOK)
            }
        }

        // 1. Check in-memory translation cache (Standard Tamil)
        getFromCache(trimmed)?.let { cached ->
            return@withContext TranslationResult(cached, isSuccess = true, source = TranslationSource.CACHE)
        }

        // 2. Check user's manual saved translations
        userTranslations[normKey]?.let { manual ->
            putInCache(trimmed, manual)
            return@withContext TranslationResult(manual, isSuccess = true, source = TranslationSource.MANUAL)
        }

        // 3. Check custom dictionary entries
        val customMatch = customEntries.firstOrNull { it.englishText.trim().equals(trimmed, ignoreCase = true) }
        if (customMatch != null) {
            putInCache(trimmed, customMatch.tamilTranslation)
            return@withContext TranslationResult(customMatch.tamilTranslation, isSuccess = true, source = TranslationSource.CUSTOM_DICT)
        }

        // 4. Character vocative check (e.g., "Peter, watch out!")
        val (characterPrefix, dialogueRemainder) = extractCharacterVocative(trimmed)
        val textToTranslate = if (characterPrefix != null) dialogueRemainder else trimmed

        if (characterPrefix != null) {
            getFromCache(textToTranslate)?.let { cachedRemainder ->
                val full = "$characterPrefix$cachedRemainder"
                putInCache(trimmed, full)
                return@withContext TranslationResult(full, isSuccess = true, source = TranslationSource.CACHE)
            }
        }

        // 5. Check offline ComicPhrasebook
        val phraseMatch = ComicPhrasebook.findPhrase(textToTranslate, speechStyle)
        if (phraseMatch != null) {
            val full = if (characterPrefix != null) "$characterPrefix$phraseMatch" else phraseMatch
            putInCache(trimmed, full)
            return@withContext TranslationResult(full, isSuccess = true, source = TranslationSource.PHRASEBOOK)
        }

        // 6. Check ML Kit offline translation only if model is physically present and confirmed downloaded
        val ctx = appContext
        val modelReady = ctx != null && isDownloadedCache == true && isModelPhysicallyPresent(ctx)

        if (modelReady) {
            try {
                val mlKitResult = suspendCancellableCoroutine<String?> { continuation ->
                    translator.translate(textToTranslate)
                        .addOnSuccessListener { translated ->
                            continuation.resume(translated)
                        }
                        .addOnFailureListener { ex ->
                            Log.w(TAG, "ML Kit translation unavailable: ${ex.message}")
                            isDownloadedCache = false
                            _modelStatus.value = TranslationModelStatus.NOT_INSTALLED
                            continuation.resume(null)
                        }
                }

                if (!mlKitResult.isNullOrBlank()) {
                    val full = if (characterPrefix != null) "$characterPrefix$mlKitResult" else mlKitResult
                    putInCache(trimmed, full)
                    return@withContext TranslationResult(full, isSuccess = true, source = TranslationSource.ML_KIT)
                }
            } catch (e: Throwable) {
                Log.e(TAG, "Exception during ML Kit translation", e)
                isDownloadedCache = false
                _modelStatus.value = TranslationModelStatus.NOT_INSTALLED
            }
        }

        // 7. Robust fallback translator with clause splitting, phrase patterns, vocabulary and transliteration
        val fallback = ComicPhrasebook.translateFallback(textToTranslate, speechStyle)
        val full = if (characterPrefix != null) "$characterPrefix$fallback" else fallback

        putInCache(trimmed, full)
        return@withContext TranslationResult(full, isSuccess = true, source = TranslationSource.FALLBACK)
    }

    suspend fun translate(
        englishText: String,
        customEntries: List<DictionaryEntry> = emptyList(),
        speechStyle: TamilSpeechStyle = TamilSpeechStyle.STANDARD,
        userTranslations: Map<String, String> = emptyMap()
    ): String {
        return translateWithResult(englishText, customEntries, speechStyle, userTranslations).tamilText
    }

    suspend fun translateBatch(
        texts: List<String>,
        customEntries: List<DictionaryEntry> = emptyList(),
        speechStyle: TamilSpeechStyle = TamilSpeechStyle.STANDARD,
        userTranslations: Map<String, String> = emptyMap()
    ): Map<String, String> {
        return texts.associateWith { text ->
            translate(text, customEntries, speechStyle, userTranslations)
        }
    }
}
