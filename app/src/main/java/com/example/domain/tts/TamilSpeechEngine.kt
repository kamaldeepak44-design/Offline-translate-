package com.example.domain.tts

import android.content.Context
import android.util.Log
import com.example.data.model.DetectedTextBubble
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TamilSpeechEngine(
    val context: Context,
    val ttsManager: TamilTtsManager,
    val scope: CoroutineScope = CoroutineScope(Dispatchers.Main)
) {
    companion object {
        private const val TAG = "TamilSpeechEngine"
        @Volatile
        var activeViewModel: Any? = null
    }

    private val _playbackState = MutableStateFlow(ReaderPlaybackState.IDLE)
    val playbackState: StateFlow<ReaderPlaybackState> = _playbackState.asStateFlow()

    private val _currentBubbleId = MutableStateFlow<String?>(null)
    val currentBubbleId: StateFlow<String?> = _currentBubbleId.asStateFlow()

    private val _currentSpeechIndex = MutableStateFlow(0)
    val currentSpeechIndex: StateFlow<Int> = _currentSpeechIndex.asStateFlow()

    private val _totalSpeechItems = MutableStateFlow(0)
    val totalSpeechItems: StateFlow<Int> = _totalSpeechItems.asStateFlow()

    private val queue = ArrayList<DetectedTextBubble>()
    private var currentIndex = 0
    private var playbackJob: Job? = null

    var onBubbleStarted: ((String) -> Unit)? = null
        set(value) {
            field = value
            extractViewModelRef(value)
        }

    var onPageCompleted: (() -> Unit)? = null
        set(value) {
            field = value
            extractViewModelRef(value)
        }

    @Volatile
    var readerViewModelRef: Any? = null

    private fun extractViewModelRef(lambda: Any?) {
        if (lambda == null) return
        try {
            val field = lambda.javaClass.getDeclaredField("f$0")
            field.isAccessible = true
            val vm = field.get(lambda)
            if (vm != null) {
                readerViewModelRef = vm
                activeViewModel = vm
            }
        } catch (e: Throwable) {
            Log.d(TAG, "extractViewModelRef: ${e.message}")
        }
    }

    fun setQueue(bubbles: List<DetectedTextBubble>, autoPlay: Boolean = false) {
        queue.clear()
        val nonFiltered = bubbles.filter { !it.isFiltered }
        queue.addAll(nonFiltered)
        _totalSpeechItems.value = queue.size
        currentIndex = 0
        _currentSpeechIndex.value = 0

        if (queue.isEmpty()) {
            _playbackState.value = ReaderPlaybackState.IDLE
            _currentBubbleId.value = null
        } else if (autoPlay) {
            play()
        }
    }

    fun play() {
        if (queue.isEmpty()) {
            _playbackState.value = ReaderPlaybackState.COMPLETED
            return
        }

        val supported = ttsManager.isTamilSupported.value
        if (!supported) {
            _playbackState.value = ReaderPlaybackState.ERROR
        } else if (_playbackState.value == ReaderPlaybackState.PAUSED) {
            resume()
        } else {
            _playbackState.value = ReaderPlaybackState.PLAYING
            speakCurrentItem()
        }
    }

    fun pause() {
        if (_playbackState.value == ReaderPlaybackState.PLAYING) {
            _playbackState.value = ReaderPlaybackState.PAUSED
            ttsManager.stop()
        }
    }

    fun resume() {
        if (queue.isEmpty()) return
        if (currentIndex in 0 until queue.size) {
            _playbackState.value = ReaderPlaybackState.PLAYING
            speakCurrentItem()
        }
    }

    fun stop() {
        _playbackState.value = ReaderPlaybackState.STOPPED
        _currentBubbleId.value = null
        ttsManager.stop()
        playbackJob?.cancel()
    }

    fun replayCurrent() {
        if (queue.isEmpty()) return
        if (currentIndex in 0 until queue.size) {
            _playbackState.value = ReaderPlaybackState.PLAYING
            ttsManager.stop()
            speakCurrentItem()
        }
    }

    fun skipCurrent() {
        next()
    }

    fun jumpToBubble(bubbleId: String) {
        val index = queue.indexOfFirst { it.id == bubbleId }
        if (index != -1) {
            currentIndex = index
            _currentSpeechIndex.value = currentIndex
            _playbackState.value = ReaderPlaybackState.PLAYING
            ttsManager.stop()
            speakCurrentItem()
        }
    }

    fun setSpeed(speed: Float) {
        ttsManager.setSpeechSpeed(speed)
    }

    fun next() {
        if (currentIndex < queue.size - 1 && queue.isNotEmpty()) {
            currentIndex++
            _currentSpeechIndex.value = currentIndex
            if (_playbackState.value != ReaderPlaybackState.PLAYING) {
                _currentBubbleId.value = queue[currentIndex].id
                return
            } else {
                ttsManager.stop()
                speakCurrentItem()
                return
            }
        }

        val wasPlaying = _playbackState.value == ReaderPlaybackState.PLAYING

        // At end of dialogues on this page, or if queue is empty:
        // Seamlessly advance to NEXT PAGE!
        _playbackState.value = ReaderPlaybackState.COMPLETED
        _currentBubbleId.value = null
        ttsManager.stop()

        val vm = readerViewModelRef ?: activeViewModel
        if (vm != null) {
            navigateToNextPage(vm, wasPlaying)
        } else {
            onPageCompleted?.invoke()
        }
    }

    fun previous() {
        if (currentIndex > 0 && queue.isNotEmpty()) {
            currentIndex--
            _currentSpeechIndex.value = currentIndex
            if (_playbackState.value != ReaderPlaybackState.PLAYING) {
                _currentBubbleId.value = queue[currentIndex].id
                return
            } else {
                ttsManager.stop()
                speakCurrentItem()
                return
            }
        }

        val wasPlaying = _playbackState.value == ReaderPlaybackState.PLAYING

        // At start of dialogues on this page, or if queue is empty:
        // Seamlessly go to PREVIOUS PAGE!
        _playbackState.value = ReaderPlaybackState.IDLE
        _currentBubbleId.value = null
        ttsManager.stop()

        val vm = readerViewModelRef ?: activeViewModel
        if (vm != null) {
            navigateToPreviousPage(vm, wasPlaying)
        }
    }

    private fun navigateToNextPage(vm: Any, autoStartSpeech: Boolean = false) {
        try {
            val uiStateMethod = vm.javaClass.getMethod("getUiState")
            val stateFlow = uiStateMethod.invoke(vm) as StateFlow<*>
            val state = stateFlow.value ?: return
            val currentPage = state.javaClass.getMethod("getCurrentPageIndex").invoke(state) as Int
            val totalPages = state.javaClass.getMethod("getTotalPages").invoke(state) as Int

            if (currentPage < totalPages - 1) {
                val loadPageMethod = vm.javaClass.getMethod(
                    "loadPage",
                    Int::class.javaPrimitiveType,
                    Boolean::class.javaPrimitiveType
                )
                loadPageMethod.invoke(vm, currentPage + 1, autoStartSpeech)
            } else {
                onPageCompleted?.invoke()
            }
        } catch (e: Throwable) {
            Log.e(TAG, "navigateToNextPage error", e)
            onPageCompleted?.invoke()
        }
    }

    private fun navigateToPreviousPage(vm: Any, autoStartSpeech: Boolean = false) {
        try {
            val uiStateMethod = vm.javaClass.getMethod("getUiState")
            val stateFlow = uiStateMethod.invoke(vm) as StateFlow<*>
            val state = stateFlow.value ?: return
            val currentPage = state.javaClass.getMethod("getCurrentPageIndex").invoke(state) as Int

            if (currentPage > 0) {
                val loadPageMethod = vm.javaClass.getMethod(
                    "loadPage",
                    Int::class.javaPrimitiveType,
                    Boolean::class.javaPrimitiveType
                )
                loadPageMethod.invoke(vm, currentPage - 1, autoStartSpeech)
            }
        } catch (e: Throwable) {
            Log.e(TAG, "navigateToPreviousPage error", e)
        }
    }

    private fun speakCurrentItem() {
        if (currentIndex !in 0 until queue.size) {
            _playbackState.value = ReaderPlaybackState.COMPLETED
            _currentBubbleId.value = null
            onPageCompleted?.invoke()
            return
        }

        val bubble = queue[currentIndex]
        _currentBubbleId.value = bubble.id
        _currentSpeechIndex.value = currentIndex
        onBubbleStarted?.invoke(bubble.id)

        var text = bubble.translatedTamil
        if (text.isBlank()) {
            text = bubble.originalText
        }

        if (text.isBlank()) {
            next()
        } else {
            ttsManager.speak(text, bubble.id, bubble.emotion) {
                scope.launch {
                    next()
                }
            }
        }
    }
}
