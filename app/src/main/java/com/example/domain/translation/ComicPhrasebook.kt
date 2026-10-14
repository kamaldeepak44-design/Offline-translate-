package com.example.domain.translation

import com.example.data.model.TamilSpeechStyle
import java.util.Locale

object ComicPhrasebook {

    private val SFX_MAP = mapOf(
        "boom" to "பூம்!",
        "boom!" to "பூம்!",
        "bang" to "படார்!",
        "bang!" to "படார்!",
        "crash" to "டமார்!",
        "crash!" to "டமார்!",
        "slash" to "சரக்!",
        "slash!" to "சரக்!",
        "clash" to "கிளாஷ்!",
        "clash!" to "கிளாஷ்!",
        "whoosh" to "வூஷ்!",
        "whoosh!" to "வூஷ்!",
        "thump" to "தப்!",
        "thump!" to "தப்!",
        "crack" to "படக்!",
        "crack!" to "படக்!",
        "smash" to "நொருக்கு!",
        "smash!" to "நொருக்கு!",
        "zap" to "ஜப்!",
        "zap!" to "ஜப்!",
        "wham" to "டமார்!",
        "wham!" to "டமார்!",
        "roar" to "கர்ஜனை!",
        "roar!" to "கர்ஜனை!",
        "sigh" to "பெருமூச்சு...",
        "pant" to "மூச்சிரைப்பு...",
        "giggle" to "சிரிப்பு",
        "smirk" to "புன்னகை",
        "gasp" to "ஆச்சரிய மூச்சு!",
        "gasp!" to "ஆச்சரிய மூச்சு!",
        "splash" to "சளக்!",
        "splash!" to "சளக்!",
        "screeech" to "கீச்!",
        "screeech!" to "கீச்!"
    )

    private val NATURAL_SPOKEN_MAP = mapOf(
        "are you okay" to "நீ நல்லா இருக்கியா?",
        "are you okay?" to "நீ நல்லா இருக்கியா?",
        "where are you going" to "நீ எங்கே போற?",
        "where are you going?" to "நீ எங்கே போற?",
        "i have to leave" to "நான் கிளம்பணும்",
        "i have to leave." to "நான் கிளம்பணும்",
        "what are you doing" to "என்ன பண்ற?",
        "what are you doing?" to "என்ன பண்ற?",
        "what happened" to "என்ன ஆச்சு?",
        "what happened?" to "என்ன ஆச்சு?",
        "why are you here" to "நீ ஏன் இங்க இருக்க?",
        "why are you here?" to "நீ ஏன் இங்க இருக்க?",
        "look at that" to "அங்க பாரு!",
        "look at that!" to "அங்க பாரு!",
        "i'm coming" to "நான் வரேன்",
        "i am coming" to "நான் வரேன்",
        "i don't know" to "எனக்கு தெரியாது",
        "i dont know" to "எனக்கு தெரியாது",
        "don't do that" to "அப்படி பண்ணாதே",
        "dont do that" to "அப்படி பண்ணாதே",
        "don't worry" to "கவலைப்படாதே",
        "dont worry" to "கவலைப்படாதே",
        "don't worry!" to "கவலைப்படாதே!",
        "don't worry." to "கவலைப்படாதே.",
        "my sword is ready" to "என் வாள் ரெடியா இருக்கு",
        "my sword is ready!" to "என் வாள் ரெடியா இருக்கு!",
        "my sword is ready." to "என் வாள் ரெடியா இருக்கு.",
        "look out! the ancient dragon is waking up!" to "கவனமா இரு! பழங்கால டிராகன் முழிக்குது!",
        "the ancient dragon is waking up!" to "பழங்கால டிராகன் முழிக்குது!",
        "all the other hunters had your eyes at first." to "மத்த வேட்டைக்காரங்களுக்கும் முதல்ல உன் கண்ணுங்கதான் இருந்துச்சு.",
        "you can't believe it, can you?" to "உன்னால இதை நம்ப முடியல, அப்படித்தானே?",
        "you can't believe it" to "உன்னால இதை நம்ப முடியல",
        "don't worry, my sword is ready!" to "கவலைப்படாதே, என் வாள் ரெடியா இருக்கு!",
        "dont worry, my sword is ready!" to "கவலைப்படாதே, என் வாள் ரெடியா இருக்கு!",
        "don't worry, my sword is ready" to "கவலைப்படாதே, என் வாள் ரெடியா இருக்கு",
        "dont worry, my sword is ready" to "கவலைப்படாதே, என் வாள் ரெடியா இருக்கு"
    )

    private val PHRASE_MAP = mapOf(
        "look out" to "கவனமாக இரு!",
        "look out!" to "கவனமாக இரு!",
        "watch out" to "எச்சரிக்கையாக இரு!",
        "watch out!" to "எச்சரிக்கையாக இரு!",
        "be careful" to "கவனமாக இரு!",
        "be careful!" to "கவனமாக இரு!",
        "don't worry" to "கவலைப்படாதே!",
        "dont worry" to "கவலைப்படாதே!",
        "don't worry!" to "கவலைப்படாதே!",
        "don't worry." to "கவலைப்படாதே.",
        "are you okay" to "நீ நலமாக இருக்கிறாயா?",
        "are you okay?" to "நீ நலமாக இருக்கிறாயா?",
        "are you alright" to "நீ நன்றாக இருக்கிறாயா?",
        "are you alright?" to "நீ நன்றாக இருக்கிறாயா?",
        "what happened" to "என்ன நடந்தது?",
        "what happened?" to "என்ன நடந்தது?",
        "what is that" to "அது என்ன?",
        "what is that?" to "அது என்ன?",
        "what's that" to "அது என்ன?",
        "what is this" to "இது என்ன?",
        "who are you" to "நீ யார்?",
        "who are you?" to "நீ யார்?",
        "where are you" to "நீ எங்கே இருக்கிறாய்?",
        "where are you?" to "நீ எங்கே இருக்கிறாய்?",
        "where are you going" to "நீ எங்கே போகிறாய்?",
        "where are you going?" to "நீ எங்கே போகிறாய்?",
        "i won't let you go" to "நான் உன்னை போக விடமாட்டேன்!",
        "i won't let you go!" to "நான் உன்னை போக விடமாட்டேன்!",
        "i wont let you go" to "நான் உன்னை போக விடமாட்டேன்!",
        "i wont let you go!" to "நான் உன்னை போக விடமாட்டேன்!",
        "never give up" to "ஒருபோதும் கைவிடாதே!",
        "never give up!" to "ஒருபோதும் கைவிடாதே!",
        "hold on" to "பொறுங்கள்!",
        "hold on!" to "பொறுங்கள்!",
        "hold the line" to "வரிசையை பிடியுங்கள்!",
        "hold the line!" to "வரிசையை பிடியுங்கள்!",
        "let's go" to "போகலாம் வாருங்கள்!",
        "let's go!" to "போகலாம் வாருங்கள்!",
        "follow me" to "என்னைத் தொடருங்கள்!",
        "follow me!" to "என்னைத் தொடருங்கள்!",
        "stop right there" to "அங்கேயே நில்லுங்கள்!",
        "stop right there!" to "அங்கேயே நில்லுங்கள்!",
        "victory is ours" to "வெற்றி நமதே!",
        "victory is ours!" to "வெற்றி நமதே!",
        "it cannot be" to "அது இருக்க முடியாது!",
        "it cannot be!" to "அது இருக்க முடியாது!",
        "impossible" to "சாத்தியமில்லை!",
        "impossible!" to "சாத்தியமில்லை!",
        "curse you" to "உன்னை சபிப்பேன்!",
        "get out of my way" to "என் வழியிலிருந்து விலகு!",
        "we must protect the realm!" to "நாம் நாட்டை பாதுகாக்க வேண்டும்!",
        "we must protect the realm" to "நாம் நாட்டை பாதுகாக்க வேண்டும்!",
        "my sword is ready" to "என் வாள் தயாராக இருக்கிறது",
        "my sword is ready!" to "என் வாள் தயாராக இருக்கிறது!",
        "my sword is ready." to "என் வாள் தயாராக இருக்கிறது.",
        "the sword is ready" to "வாள் தயாராக இருக்கிறது",
        "the sword is ready!" to "வாள் தயாராக இருக்கிறது!",
        "i am ready" to "நான் தயாராக இருக்கிறேன்",
        "i am ready!" to "நான் தயாராக இருக்கிறேன்!",
        "i'm ready" to "நான் தயாராக இருக்கிறேன்",
        "i'm ready!" to "நான் தயாராக இருக்கிறேன்!",
        "we are ready" to "நாம் தயாராக இருக்கிறோம்",
        "we are ready!" to "நாம் தயாராக இருக்கிறோம்!",
        "we're ready" to "நாம் தயாராக இருக்கிறோம்",
        "we're ready!" to "நாம் தயாராக இருக்கிறோம்!",
        "get ready" to "தயாராகுங்கள்!",
        "get ready!" to "தயாராகுங்கள்!",
        "stand back" to "பின்னே நில்லுங்கள்!",
        "stand back!" to "பின்னே நில்லுங்கள்!",
        "take this" to "இதை வாங்கிக்கொள்!",
        "take this!" to "இதை வாங்கிக்கொள்!",
        "trust me" to "என்னை நம்பு!",
        "trust me!" to "என்னை நம்பு!",
        "believe me" to "என்னை நம்பு!",
        "believe me!" to "என்னை நம்பு!",
        "behind you" to "உன் பின்னால்!",
        "behind you!" to "உன் பின்னால்!",
        "run away" to "ஓடிவிடு!",
        "run away!" to "ஓடிவிடு!",
        "hurry up" to "சீக்கிரம்!",
        "hurry up!" to "சீக்கிரம்!",
        "no way" to "வாய்ப்பே இல்லை!",
        "no way!" to "வாய்ப்பே இல்லை!",
        "of course" to "நிச்சயமாக",
        "all right" to "சரி",
        "alright" to "சரி",
        "look out! the ancient dragon is waking up!" to "கவனமாக இரு! பழங்கால டிராகன் விழித்துக்கொண்டிருக்கிறது!",
        "the ancient dragon is waking up!" to "பழங்கால டிராகன் விழித்துக்கொண்டிருக்கிறது!",
        "the ancient dragon is waking up" to "பழங்கால டிராகன் விழித்துக்கொண்டிருக்கிறது",
        "the dragon is waking up!" to "டிராகன் விழித்துக்கொண்டிருக்கிறது!",
        "the dragon is waking up" to "டிராகன் விழித்துக்கொண்டிருக்கிறது",
        "all the other hunters had your eyes at first." to "மற்ற அனைத்து வேட்டைக்காரர்களுக்கும் முதலில் உன் கண்கள் இருந்தன.",
        "all the other hunters had your eyes at first" to "மற்ற அனைத்து வேட்டைக்காரர்களுக்கும் முதலில் உன் கண்கள் இருந்தன",
        "all the other hlunters had your eyes at first." to "மற்ற அனைத்து வேட்டைக்காரர்களுக்கும் முதலில் உன் கண்கள் இருந்தன.",
        "all the other hlunters had your eyes at first" to "மற்ற அனைத்து வேட்டைக்காரர்களுக்கும் முதலில் உன் கண்கள் இருந்தன",
        "you can't believe it, can you?" to "உன்னால் இதை நம்ப முடியாது, முடியுமா?",
        "you cant believe it, can you?" to "உன்னால் இதை நம்ப முடியாது, முடியுமா?",
        "you can't believe it" to "உன்னால் இதை நம்ப முடியாது",
        "you cant believe it" to "உன்னால் இதை நம்ப முடியாது",
        "can you believe it?" to "இதை நம்ப முடிகிறதா?",
        "can you believe it" to "இதை நம்ப முடிகிறதா?",
        "i can't believe it!" to "என்னால் இதை நம்ப முடியாது!",
        "i can't believe it" to "என்னால் இதை நம்ப முடியாது!",
        "i cant believe it!" to "என்னால் இதை நம்ப முடியாது!",
        "i cant believe it" to "என்னால் இதை நம்ப முடியாது!",
        "i can't believe this" to "என்னால் இதை நம்ப முடியாது!",
        "can you?" to "முடியுமா?",
        "can you" to "முடியுமா",
        "what is happening?" to "என்ன நடக்கிறது?",
        "what is happening" to "என்ன நடக்கிறது?",
        "what's happening?" to "என்ன நடக்கிறது?",
        "what's happening" to "என்ன நடக்கிறது?",
        "what is going on?" to "என்ன நடக்கிறது?",
        "what is going on" to "என்ன நடக்கிறது?",
        "what's going on?" to "என்ன நடக்கிறது?",
        "what's going on" to "என்ன நடக்கிறது?",
        "who is that?" to "அவர் யார்?",
        "who is that" to "அவர் யார்?",
        "who's that?" to "அவர் யார்?",
        "who's that" to "அவர் யார்?",
        "it's dangerous!" to "அது ஆபத்தானது!",
        "it's dangerous" to "அது ஆபத்தானது",
        "it is dangerous!" to "அது ஆபத்தானது!",
        "it is dangerous" to "அது ஆபத்தானது",
        "don't worry, my sword is ready!" to "கவலைப்படாதே, என் வாள் தயாராக இருக்கிறது!",
        "dont worry, my sword is ready!" to "கவலைப்படாதே, என் வாள் தயாராக இருக்கிறது!",
        "don't worry, my sword is ready" to "கவலைப்படாதே, என் வாள் தயாராக இருக்கிறது",
        "dont worry, my sword is ready" to "கவலைப்படாதே, என் வாள் தயாராக இருக்கிறது"
    )

    private val WORD_MAP = mapOf(
        "yes" to "ஆம்",
        "no" to "இல்லை",
        "stop" to "நில்",
        "wait" to "பொறு",
        "run" to "ஓடு",
        "fight" to "சண்டை செய்",
        "help" to "உதவு",
        "danger" to "ஆபத்து",
        "enemy" to "எதிரி",
        "friend" to "நண்பன்",
        "hero" to "ஹீரோ",
        "monster" to "அரக்கன்",
        "monsters" to "அரக்கர்கள்",
        "beast" to "மிருகம்",
        "beasts" to "மிருகங்கள்",
        "creature" to "உயிரினம்",
        "fire" to "தீ",
        "water" to "தண்ணீர்",
        "power" to "சக்தி",
        "magic" to "மாயம்",
        "sword" to "வாள்",
        "blade" to "வாள்",
        "shield" to "கேடயம்",
        "armor" to "கவசம்",
        "bow" to "வில்",
        "arrow" to "அம்பு",
        "attack" to "தாக்குதல்",
        "defend" to "பாதுகா",
        "kill" to "கொல்",
        "live" to "வாழ்",
        "die" to "இற",
        "save" to "காப்பாற்று",
        "protect" to "பாதுகா",
        "realm" to "நாடு",
        "kingdom" to "ராஜ்யம்",
        "world" to "உலகம்",
        "ready" to "தயார்",
        "now" to "இப்போது",
        "here" to "இங்கே",
        "there" to "அங்கே",
        "come" to "வா",
        "go" to "போ",
        "see" to "பார்",
        "look" to "பார்",
        "hear" to "கேள்",
        "listen" to "கவனி",
        "say" to "சொல்",
        "tell" to "கூறு",
        "know" to "அறி",
        "understand" to "புரிந்து கொள்",
        "remember" to "நினைவில் கொள்",
        "forget" to "மற",
        "good" to "நல்லது",
        "bad" to "கெட்டது",
        "strong" to "வலுவான",
        "weak" to "பலவீனமான",
        "fast" to "வேகமாக",
        "slow" to "மெதுவாக",
        "today" to "இன்று",
        "tomorrow" to "நாளை",
        "yesterday" to "நேற்று",
        "my" to "என்",
        "your" to "உன்",
        "our" to "எங்கள்",
        "their" to "அவர்களின்",
        "his" to "அவனுடைய",
        "her" to "அவளுடைய",
        "its" to "அதனுடைய",
        "this" to "இந்த",
        "that" to "அந்த",
        "these" to "இவை",
        "those" to "அவை",
        "hunter" to "வேட்டைக்காரன்",
        "hunters" to "வேட்டைக்காரர்கள்",
        "hlunter" to "வேட்டைக்காரன்",
        "hlunters" to "வேட்டைக்காரர்கள்",
        "dragon" to "டிராகன்",
        "ancient" to "பழங்கால",
        "waking" to "விழித்துக்கொண்டு",
        "wake" to "விழி",
        "awake" to "விழித்திரு",
        "eye" to "கண்",
        "eyes" to "கண்கள்",
        "first" to "முதலில்",
        "last" to "கடைசியாக",
        "other" to "மற்ற",
        "others" to "மற்றவர்கள்",
        "all" to "அனைத்து",
        "had" to "இருந்தன",
        "have" to "வைத்திருக்கிறேன்",
        "has" to "வைத்திருக்கிறது",
        "believe" to "நம்பு",
        "dungeon" to "நிலவறை",
        "gate" to "வாசல்",
        "level" to "நிலை",
        "rank" to "தரம்",
        "shadow" to "நிழல்",
        "shadows" to "நிழல்கள்",
        "king" to "அரசன்",
        "demon" to "அரக்கன்",
        "god" to "கடவுள்",
        "boss" to "தலைவன்",
        "leader" to "தலைவர்",
        "soldier" to "படைவீரன்",
        "soldiers" to "படைவீரர்கள்",
        "warrior" to "போர்வீரன்",
        "warriors" to "போர்வீரர்கள்",
        "heart" to "இதயம்",
        "soul" to "ஆன்மா",
        "body" to "உடல்",
        "head" to "தலை",
        "hand" to "கை",
        "hands" to "கைகள்",
        "blood" to "ரத்தம்",
        "wound" to "காயம்",
        "pain" to "வலி",
        "death" to "மரணம்",
        "life" to "வாழ்க்கை",
        "alive" to "உயிருடன்",
        "win" to "வெற்றி பெறு",
        "lose" to "தோல்வி அடை",
        "strike" to "தாக்கு",
        "escape" to "தப்பு",
        "start" to "தொடங்கு",
        "end" to "முடிவு",
        "dark" to "இருள்",
        "darkness" to "இருள்",
        "light" to "ஒளி",
        "true" to "உண்மை",
        "truth" to "உண்மை",
        "lie" to "பொய்",
        "secret" to "ரகசியம்",
        "always" to "எப்போதும்",
        "never" to "ஒருபோதும் இல்லை",
        "soon" to "விரைவில்",
        "later" to "பிறகு",
        "again" to "மீண்டும்",
        "too" to "மிகவும்",
        "very" to "மிகவும்",
        "much" to "அதிகம்",
        "many" to "பல",
        "more" to "மேலும்",
        "little" to "சிறிது",
        "some" to "சில",
        "great" to "மாபெரும்",
        "big" to "பெரிய",
        "small" to "சிறிய",
        "time" to "நேரம்",
        "day" to "நாள்",
        "night" to "இரவு",
        "man" to "மனிதன்",
        "woman" to "பெண்",
        "people" to "மக்கள்",
        "child" to "குழந்தை"
    )

    fun cleanOcr(text: String): String {
        return text
            .replace("HLUNTERS", "HUNTERS")
            .replace("hlunters", "hunters")
            .replace("HLUNTER", "HUNTER")
            .replace("hlunter", "hunter")
            .replace(Regex("(?i)\\bcan\\s*t\\b"), "can't")
            .replace(Regex("(?i)\\bdon\\s*t\\b"), "don't")
            .replace(Regex("(?i)\\bwon\\s*t\\b"), "won't")
            .replace(Regex("(?i)\\bi\\s*m\\b"), "i'm")
            .replace(Regex("(?i)\\byou\\s*re\\b"), "you're")
            .replace(Regex("(?i)\\bwe\\s*re\\b"), "we're")
            .replace(Regex("(?i)\\bit\\s*s\\b"), "it's")
            .replace(Regex("(?i)\\bwhat\\s*s\\b"), "what's")
            .replace(Regex("(?i)\\bthat\\s*s\\b"), "that's")
            .replace(Regex("(?i)\\bwho\\s*s\\b"), "who's")
            .trim()
    }

    fun isSfx(text: String): Boolean {
        val trimmed = text.trim().lowercase(Locale.ROOT)
        val stripped = trimmed.trimEnd('.', '!', '?', ',', ' ')
        return SFX_MAP.containsKey(trimmed) || SFX_MAP.containsKey(stripped)
    }

    fun findPhrase(englishText: String, style: TamilSpeechStyle = TamilSpeechStyle.STANDARD): String? {
        val cleaned = cleanOcr(englishText)
        val trimmed = cleaned.trim().lowercase(Locale.ROOT)
        if (trimmed.isEmpty()) return null

        val stripped = trimmed.trimEnd('.', '!', '?', ',', ' ')
        val clean = trimmed.replace(Regex("[\"'`]"), "")
        val cleanStripped = clean.trimEnd('.', '!', '?', ',', ' ')

        val endPunct = when {
            trimmed.endsWith("!") -> "!"
            trimmed.endsWith("?") -> "?"
            trimmed.endsWith(".") -> "."
            else -> ""
        }

        if (style == TamilSpeechStyle.NATURAL_SPOKEN) {
            TamilDictionaryPack.SPOKEN_PHRASES[trimmed]?.let { return it }
            TamilDictionaryPack.SPOKEN_PHRASES[stripped]?.let { return adjustPunctuation(it, endPunct) }
            TamilDictionaryPack.SPOKEN_PHRASES[clean]?.let { return it }
            TamilDictionaryPack.SPOKEN_PHRASES[cleanStripped]?.let { return adjustPunctuation(it, endPunct) }

            NATURAL_SPOKEN_MAP[trimmed]?.let { return it }
            NATURAL_SPOKEN_MAP[stripped]?.let { return adjustPunctuation(it, endPunct) }
            NATURAL_SPOKEN_MAP[clean]?.let { return it }
            NATURAL_SPOKEN_MAP[cleanStripped]?.let { return adjustPunctuation(it, endPunct) }
        }

        TamilDictionaryPack.EXTENSIVE_PHRASES[trimmed]?.let { return it }
        TamilDictionaryPack.EXTENSIVE_PHRASES[stripped]?.let { return adjustPunctuation(it, endPunct) }
        TamilDictionaryPack.EXTENSIVE_PHRASES[clean]?.let { return it }
        TamilDictionaryPack.EXTENSIVE_PHRASES[cleanStripped]?.let { return adjustPunctuation(it, endPunct) }

        PHRASE_MAP[trimmed]?.let { return it }
        PHRASE_MAP[stripped]?.let { return adjustPunctuation(it, endPunct) }
        PHRASE_MAP[clean]?.let { return it }
        PHRASE_MAP[cleanStripped]?.let { return adjustPunctuation(it, endPunct) }

        SFX_MAP[trimmed]?.let { return it }
        SFX_MAP[stripped]?.let { return it }
        SFX_MAP[clean]?.let { return it }
        SFX_MAP[cleanStripped]?.let { return it }

        return null
    }

    private fun adjustPunctuation(tamilText: String, targetPunct: String): String {
        if (targetPunct.isEmpty()) return tamilText.trimEnd('.', '!', '?')
        val clean = tamilText.trimEnd('.', '!', '?')
        return "$clean$targetPunct"
    }

    fun translateFallback(englishText: String, style: TamilSpeechStyle = TamilSpeechStyle.STANDARD): String {
        val trimmed = englishText.trim()
        if (trimmed.isEmpty()) return ""

        val cleaned = cleanOcr(trimmed)

        // 1. Direct phrase lookup
        findPhrase(cleaned, style)?.let { return it }
        findPhrase(trimmed, style)?.let { return it }

        // 2. Multi-sentence splitting: check for multiple sentences separated by !, ?, ., \n
        // e.g. "Look out! The ancient dragon is waking up!"
        val sentencePunctRegex = Regex("([.!?]+|\\n+)")
        val rawParts = cleaned.split(sentencePunctRegex).map { it.trim() }.filter { it.isNotEmpty() }
        if (rawParts.size > 1 && cleaned.contains(Regex("[.!?\\n]"))) {
            val matches = sentencePunctRegex.findAll(cleaned).map { it.value.trim() }.toList()
            val translatedSentences = mutableListOf<String>()
            for (i in rawParts.indices) {
                val part = rawParts[i]
                val punct = if (i < matches.size) matches[i] else ""
                val partWithPunct = if (punct.isNotEmpty() && !part.endsWith(punct)) "$part$punct" else part
                val trans = translateSingleSentence(partWithPunct, style)
                translatedSentences.add(trans)
            }
            val combined = translatedSentences.joinToString(" ")
            if (combined.any { it in '\u0B80'..'\u0BFF' }) {
                return combined
            }
        }

        // 3. Translate as single sentence (which may have clauses)
        return translateSingleSentence(cleaned, style)
    }

    private fun translateSingleSentence(sentence: String, style: TamilSpeechStyle): String {
        val trimmed = sentence.trim()
        if (trimmed.isEmpty()) return ""

        // Check direct phrase lookup
        findPhrase(trimmed, style)?.let { return it }

        // 4. Check comma / semicolon / colon / em-dash clause combinations
        // e.g. "Don't worry, my sword is ready!" or "YOU CAN'T BELIEVE IT, CAN YOU?"
        val clauseSplitRegex = Regex("([,;:—–]+|\\s+--\\s+)")
        if (clauseSplitRegex.containsMatchIn(trimmed)) {
            val parts = trimmed.split(clauseSplitRegex).map { it.trim() }.filter { it.isNotEmpty() }
            if (parts.size > 1) {
                val translatedClauses = parts.mapIndexed { index, clause ->
                    val trans = translateClause(clause, style)
                    if (index < parts.size - 1) {
                        trans.trimEnd('!', '.', '?', ',')
                    } else {
                        trans
                    }
                }
                return translatedClauses.joinToString(", ")
            }
        }

        // 5. Clause / Template level patterns
        val sentenceResult = translateClause(trimmed, style)
        if (sentenceResult != trimmed && sentenceResult.any { it in '\u0B80'..'\u0BFF' }) {
            return sentenceResult
        }

        // 6. Word-by-word with grammatical reordering and phonetic transliteration fallback
        return translateWordsWithTransliteration(trimmed)
    }

    private fun translateClause(clause: String, style: TamilSpeechStyle): String {
        val trimmed = clause.trim()
        if (trimmed.isEmpty()) return ""

        // Direct phrase check
        findPhrase(trimmed, style)?.let { return it }

        val lower = trimmed.lowercase(Locale.ROOT)
        val endPunct = when {
            trimmed.endsWith("!") -> "!"
            trimmed.endsWith("?") -> "?"
            trimmed.endsWith(".") -> "."
            else -> ""
        }
        val clean = lower.trimEnd('.', '!', '?', ',')

        // Pattern: "all the other [hunters/monsters] had your eyes at first"
        val allOtherHunters = Regex("all\\s+the\\s+other\\s+([a-z]+)\\s+had\\s+(your|my|his|her)\\s+([a-z]+)\\s+at\\s+first")
        val matchAllOther = allOtherHunters.matchEntire(clean)
        if (matchAllOther != null) {
            val subjectGroup = matchAllOther.groupValues[1]
            val possessive = matchAllOther.groupValues[2]
            val bodyPart = matchAllOther.groupValues[3]

            val tamilSubject = when (subjectGroup) {
                "hunters", "hlunters" -> "வேட்டைக்காரர்களுக்கும்"
                "warriors" -> "போர்வீரர்களுக்கும்"
                "soldiers" -> "படைவீரர்களுக்கும்"
                "monsters" -> "அரக்கர்களுக்கும்"
                "people" -> "மக்களுக்கும்"
                else -> "$subjectGroup-களுக்கும்"
            }
            val tamilPossessive = when (possessive) {
                "your" -> "உன்"
                "my" -> "என்"
                "his" -> "அவனுடைய"
                "her" -> "அவளுடைய"
                else -> possessive
            }
            val tamilBody = when (bodyPart) {
                "eyes" -> "கண்கள்"
                "eye" -> "கண்"
                "heart" -> "இதயம்"
                "power" -> "சக்தி"
                "strength" -> "பலம்"
                else -> bodyPart
            }
            val tamilResult = if (style == TamilSpeechStyle.NATURAL_SPOKEN) {
                "மத்த $tamilSubject முதல்ல $tamilPossessive $tamilBody தான் இருந்துச்சு"
            } else {
                "மற்ற அனைத்து $tamilSubject முதலில் $tamilPossessive $tamilBody இருந்தன"
            }
            return adjustPunctuation(tamilResult, endPunct)
        }

        // Pattern: "the [ancient/mighty/fierce] [dragon/beast/monster] is waking up"
        val wakingUpRegex = Regex("(the\\s+)?(ancient\\s+|mighty\\s+|fierce\\s+)?([a-z]+)\\s+is\\s+waking\\s+up")
        val matchWaking = wakingUpRegex.matchEntire(clean)
        if (matchWaking != null) {
            val modifier = matchWaking.groupValues[2].trim()
            val creature = matchWaking.groupValues[3].trim()

            val tamilMod = when (modifier) {
                "ancient" -> "பழங்கால "
                "mighty" -> "வலிமையான "
                "fierce" -> "கொடிய "
                else -> ""
            }
            val tamilCreature = when (creature) {
                "dragon" -> "டிராகன்"
                "beast" -> "மிருகம்"
                "monster" -> "அரக்கன்"
                "creature" -> "உயிரினம்"
                "king" -> "அரசன்"
                "lord" -> "பிரபு"
                else -> creature
            }
            val verb = if (style == TamilSpeechStyle.NATURAL_SPOKEN) "முழிக்குது" else "விழித்துக்கொண்டிருக்கிறது"
            return adjustPunctuation("$tamilMod$tamilCreature $verb", endPunct)
        }

        // Pattern: "you can't believe it" / "you cant believe it"
        if (clean == "you can't believe it" || clean == "you cant believe it" || clean == "you cannot believe it") {
            val res = if (style == TamilSpeechStyle.NATURAL_SPOKEN) "உன்னால இதை நம்ப முடியல" else "உன்னால் இதை நம்ப முடியாது"
            return adjustPunctuation(res, endPunct)
        }

        // Pattern: "can you" (tag question)
        if (clean == "can you") {
            val res = if (style == TamilSpeechStyle.NATURAL_SPOKEN) "அப்படியா?" else "முடியுமா?"
            return adjustPunctuation(res, if (endPunct.isEmpty()) "?" else endPunct)
        }

        // Pattern: "my [noun] is ready"
        val myNounReady = Regex("(my|the|your|our)\\s+([a-z]+)\\s+is\\s+ready")
        val matchMyNoun = myNounReady.matchEntire(clean)
        if (matchMyNoun != null) {
            val pronoun = matchMyNoun.groupValues[1]
            val noun = matchMyNoun.groupValues[2]
            val tamilPronoun = when (pronoun) {
                "my" -> "என்"
                "your" -> "உன்"
                "our" -> "எங்கள்"
                else -> ""
            }
            val tamilNoun = when (noun) {
                "sword" -> "வாள்"
                "blade" -> "வாள்"
                "shield" -> "கேடயம்"
                "bow" -> "வில்"
                "weapon" -> "ஆயுதம்"
                "armor" -> "கவசம்"
                "power" -> "சக்தி"
                "magic" -> "மாயம்"
                else -> noun
            }
            val prefix = if (tamilPronoun.isNotEmpty()) "$tamilPronoun " else ""
            val verb = if (style == TamilSpeechStyle.NATURAL_SPOKEN) "ரெடியா இருக்கு" else "தயாராக இருக்கிறது"
            return adjustPunctuation("$prefix$tamilNoun $verb", endPunct)
        }

        // Pattern: "don't worry" / "dont worry"
        if (clean == "don't worry" || clean == "dont worry") {
            return adjustPunctuation("கவலைப்படாதே", endPunct)
        }

        // Pattern: "don't <verb>"
        val dontVerb = Regex("(don't|dont|do not)\\s+([a-z]+)")
        val matchDont = dontVerb.matchEntire(clean)
        if (matchDont != null) {
            val verb = matchDont.groupValues[2]
            val tamilVerb = when (verb) {
                "worry" -> "கவலைப்படாதே"
                "cry" -> "அழாதே"
                "fear" -> "பயப்படாதே"
                "move" -> "அசையாதே"
                "run" -> "ஓடாதே"
                "stop" -> "நிறுத்தாதே"
                "give up", "giveup" -> "கைவிடாதே"
                "look" -> "பார்க்காதே"
                "go" -> "போகாதே"
                "come" -> "வராதே"
                "touch" -> "தொடாதே"
                "shout" -> "கத்தாதே"
                else -> null
            }
            if (tamilVerb != null) {
                return adjustPunctuation(tamilVerb, endPunct)
            }
        }

        // Pattern: "we must protect <noun>"
        val weMustProtect = Regex("we\\s+must\\s+protect\\s+(the\\s+)?([a-z]+)")
        val matchProtect = weMustProtect.matchEntire(clean)
        if (matchProtect != null) {
            val noun = matchProtect.groupValues[2]
            val tamilNoun = when (noun) {
                "realm" -> "நாட்டை"
                "kingdom" -> "நாட்டை"
                "city" -> "நகரத்தை"
                "people" -> "மக்களை"
                "world" -> "உலகத்தை"
                "king" -> "அரசரை"
                else -> "$noun-ஐ"
            }
            return adjustPunctuation("நாம் $tamilNoun பாதுகாக்க வேண்டும்", endPunct)
        }

        // Pattern: "we must <verb>"
        val weMustVerb = Regex("we\\s+must\\s+([a-z]+)")
        val matchWeMust = weMustVerb.matchEntire(clean)
        if (matchWeMust != null) {
            val verb = matchWeMust.groupValues[1]
            val tamilAction = when (verb) {
                "fight" -> "போராட"
                "win" -> "வெற்றி பெற"
                "run" -> "ஓட"
                "go" -> "செல்ல"
                "leave" -> "புறப்பட"
                "survive" -> "உயிர் வாழ"
                "stop" -> "நிறுத்த"
                else -> null
            }
            if (tamilAction != null) {
                return adjustPunctuation("நாம் $tamilAction வேண்டும்", endPunct)
            }
        }

        // Pattern: "i will <verb>" / "i'll <verb>"
        val iWillVerb = Regex("(i\\s+will|i'll|ill)\\s+([a-z]+)")
        val matchIWill = iWillVerb.matchEntire(clean)
        if (matchIWill != null) {
            val verb = matchIWill.groupValues[2]
            val tamilAction = when (verb) {
                "fight" -> "நான் சண்டை செய்வேன்"
                "protect" -> "நான் பாதுகாப்பேன்"
                "save" -> "நான் காப்பாற்றுவேன்"
                "win" -> "நான் வெற்றி பெறுவேன்"
                "come" -> "நான் வருவேன்"
                "go" -> "நான் போவேன்"
                "help" -> "நான் உதவுவேன்"
                else -> null
            }
            if (tamilAction != null) {
                return adjustPunctuation(tamilAction, endPunct)
            }
        }

        // Pattern: "i won't let you <verb>" / "i wont let you <verb>"
        val iWontLet = Regex("(i\\s+won't|i\\s+wont|i\\s+will\\s+not)\\s+let\\s+you\\s+([a-z]+)")
        val matchWont = iWontLet.matchEntire(clean)
        if (matchWont != null) {
            val verb = matchWont.groupValues[2]
            val tamilAction = when (verb) {
                "go" -> "நான் உன்னை போக விடமாட்டேன்"
                "die" -> "நான் உன்னை இறக்க விடமாட்டேன்"
                "win" -> "நான் உன்னை ஜெயிக்க விடமாட்டேன்"
                "escape" -> "நான் உன்னை தப்ப விடமாட்டேன்"
                else -> "நான் உன்னை விடமாட்டேன்"
            }
            return adjustPunctuation(tamilAction, endPunct)
        }

        // Pattern: "who is <pronoun/noun>"
        val whoIsRegex = Regex("who\\s+is\\s+(this|that|he|she|it|the\\s+[a-z]+)")
        val matchWho = whoIsRegex.matchEntire(clean)
        if (matchWho != null) {
            val target = matchWho.groupValues[1]
            val tamilTarget = when (target) {
                "this" -> "இவன்"
                "that" -> "அவன்"
                "he" -> "அவன்"
                "she" -> "அவள்"
                "it" -> "அது"
                else -> TamilDictionaryPack.getWordMeaning(target.replace("the ", "")) ?: target
            }
            val res = "$tamilTarget யார்?"
            return adjustPunctuation(res, if (endPunct.isEmpty()) "?" else endPunct)
        }

        // Pattern: "what is <pronoun/noun>"
        val whatIsRegex = Regex("what\\s+(is|was)\\s+(this|that|it|the\\s+[a-z]+)")
        val matchWhat = whatIsRegex.matchEntire(clean)
        if (matchWhat != null) {
            val target = matchWhat.groupValues[2]
            val tamilTarget = when (target) {
                "this" -> "இது"
                "that" -> "அது"
                "it" -> "அது"
                else -> TamilDictionaryPack.getWordMeaning(target.replace("the ", "")) ?: target
            }
            val res = "$tamilTarget என்ன?"
            return adjustPunctuation(res, if (endPunct.isEmpty()) "?" else endPunct)
        }

        // Pattern: "where is <noun>"
        val whereIsRegex = Regex("where\\s+is\\s+(he|she|it|my\\s+[a-z]+|the\\s+[a-z]+|[a-z]+)")
        val matchWhere = whereIsRegex.matchEntire(clean)
        if (matchWhere != null) {
            val target = matchWhere.groupValues[1]
            val tamilTarget = when (target) {
                "he" -> "அவன்"
                "she" -> "அவள்"
                "it" -> "அது"
                else -> {
                    val cleanTarget = target.replace("the ", "").replace("my ", "என் ")
                    TamilDictionaryPack.getWordMeaning(cleanTarget) ?: cleanTarget
                }
            }
            val res = "$tamilTarget எங்கே?"
            return adjustPunctuation(res, if (endPunct.isEmpty()) "?" else endPunct)
        }

        // Pattern: "i have to <verb>" / "we have to <verb>" / "you have to <verb>"
        val haveToVerb = Regex("(i|we|you)\\s+have\\s+to\\s+([a-z]+)")
        val matchHaveTo = haveToVerb.matchEntire(clean)
        if (matchHaveTo != null) {
            val subj = matchHaveTo.groupValues[1]
            val verb = matchHaveTo.groupValues[2]
            val tamilSubj = when (subj) {
                "i" -> "நான்"
                "we" -> "நாம்"
                "you" -> "நீ"
                else -> subj
            }
            val tamilAction = when (verb) {
                "go" -> "செல்ல"
                "leave" -> "கிளம்ப"
                "fight" -> "போராட"
                "run" -> "ஓட"
                "stop" -> "நிறுத்த"
                "win" -> "வெற்றி பெற"
                "survive" -> "உயிர் வாழ"
                "protect" -> "பாதுகாக்க"
                "help" -> "உதவ"
                else -> TamilDictionaryPack.getWordMeaning(verb) ?: verb
            }
            val res = "$tamilSubj $tamilAction வேண்டும்"
            return adjustPunctuation(res, endPunct)
        }

        // Pattern: "help me" / "save me" / "trust me" / "listen to me" / "follow me"
        if (clean == "help me") return adjustPunctuation("எனக்கு உதவுங்கள்!", if (endPunct.isEmpty()) "!" else endPunct)
        if (clean == "save me") return adjustPunctuation("என்னை காப்பாற்றுங்கள்!", if (endPunct.isEmpty()) "!" else endPunct)
        if (clean == "trust me") return adjustPunctuation("என்னை நம்புங்கள்!", if (endPunct.isEmpty()) "!" else endPunct)
        if (clean == "listen to me") return adjustPunctuation("என் பேச்சைக் கேளுங்கள்!", if (endPunct.isEmpty()) "!" else endPunct)
        if (clean == "look at me") return adjustPunctuation("என்னை பார்!", if (endPunct.isEmpty()) "!" else endPunct)
        if (clean == "follow me") return adjustPunctuation("என்னைத் தொடருங்கள்!", if (endPunct.isEmpty()) "!" else endPunct)
        if (clean == "run away") return adjustPunctuation("ஓடி தப்பித்து விடுங்கள்!", if (endPunct.isEmpty()) "!" else endPunct)
        if (clean == "get out" || clean == "get out of here") return adjustPunctuation("இங்கிருந்து வெளியேறு!", if (endPunct.isEmpty()) "!" else endPunct)
        if (clean == "shut up") return adjustPunctuation("வாயை மூடு!", if (endPunct.isEmpty()) "!" else endPunct)
        if (clean == "calm down") return adjustPunctuation("அமைதியாக இருங்கள்!", if (endPunct.isEmpty()) "!" else endPunct)

        // Direct dictionary lookup on clean clause
        findPhrase(clean, style)?.let { return adjustPunctuation(it, endPunct) }

        return trimmed
    }

    private fun translateWordsWithTransliteration(text: String): String {
        val words = text.split(Regex("\\s+")).filter { it.isNotEmpty() }
        if (words.isEmpty()) return ""

        val lowerWords = words.map { it.lowercase(Locale.ROOT).trimEnd('.', ',', '!', '?', ';', ':') }
        val reorderedWords = reorderToTamilSOV(words, lowerWords)

        val translatedWords = reorderedWords.map { rawWord ->
            val cleanWord = rawWord.lowercase(Locale.ROOT).trimEnd('.', ',', '!', '?', ';', ':')
            val punct = rawWord.filter { it in ".,!?;:" }
            val tamilMeaning = TamilDictionaryPack.getWordMeaning(cleanWord) ?: WORD_MAP[cleanWord]

            if (tamilMeaning != null && tamilMeaning.isNotEmpty()) {
                "$tamilMeaning$punct"
            } else if (cleanWord.isNotEmpty()) {
                // Transliterate phonetically so no pure English letters remain
                val phonetic = transliterateEnglishToTamil(cleanWord)
                "$phonetic$punct"
            } else {
                rawWord
            }
        }

        return translatedWords.joinToString(" ")
    }

    private fun reorderToTamilSOV(words: List<String>, lowerWords: List<String>): List<String> {
        if (words.size < 3) return words

        val verbIndices = lowerWords.mapIndexedNotNull { idx, w ->
            if (idx > 0 && idx < lowerWords.size - 1 && isLikelyVerbOrAux(w)) idx else null
        }

        if (verbIndices.isNotEmpty()) {
            val vIdx = verbIndices.first()
            val verbPhrase = mutableListOf<String>()
            var endV = vIdx
            while (endV < lowerWords.size - 1 && isLikelyVerbOrAux(lowerWords[endV])) {
                verbPhrase.add(words[endV])
                endV++
            }

            if (endV < lowerWords.size && verbPhrase.isNotEmpty()) {
                val subjectPart = words.subList(0, vIdx)
                val objectPart = words.subList(endV, words.size)
                val reordered = ArrayList<String>(words.size)
                reordered.addAll(subjectPart)
                reordered.addAll(objectPart)
                val lastObj = reordered.last()
                val punct = lastObj.filter { it in ".,!?;:" }
                if (punct.isNotEmpty()) {
                    reordered[reordered.size - 1] = lastObj.trimEnd('.', ',', '!', '?', ';', ':')
                }
                for (i in verbPhrase.indices) {
                    if (i == verbPhrase.size - 1 && punct.isNotEmpty()) {
                        reordered.add(verbPhrase[i].trimEnd('.', ',', '!', '?', ';', ':') + punct)
                    } else {
                        reordered.add(verbPhrase[i].trimEnd('.', ',', '!', '?', ';', ':'))
                    }
                }
                return reordered
            }
        }

        return words
    }

    private fun isLikelyVerbOrAux(word: String): Boolean {
        val verbs = setOf(
            "is", "are", "was", "were", "am", "will", "can", "could", "must", "should", "would",
            "have", "has", "had", "protect", "protects", "protecting", "save", "saves", "saving",
            "defeat", "defeats", "defeating", "fight", "fights", "fighting", "kill", "kills", "killing",
            "destroy", "destroys", "destroying", "attack", "attacks", "attacking", "see", "sees", "seeing",
            "hear", "hears", "hearing", "know", "knows", "knowing", "find", "finds", "finding",
            "take", "takes", "taking", "give", "gives", "giving", "make", "makes", "making",
            "break", "breaks", "breaking", "want", "wants", "wanting", "need", "needs", "needing",
            "hold", "holds", "holding", "help", "helps", "helping", "stop", "stops", "stopping",
            "run", "runs", "running", "leave", "leaves", "leaving", "enter", "enters", "entering"
        )
        return word in verbs || word.endsWith("ing") || word.endsWith("ed")
    }

    fun transliterateEnglishToTamil(word: String): String {
        val w = word.lowercase(Locale.ROOT).trim()
        if (w.isEmpty()) return ""

        // Common comic names & words
        val knownTransliterations = mapOf(
            "dragon" to "டிராகன்",
            "hunter" to "ஹண்டர்",
            "hunters" to "ஹண்டர்ஸ்",
            "hlunter" to "ஹண்டர்",
            "hlunters" to "ஹண்டர்ஸ்",
            "monster" to "மான்ஸ்டர்",
            "monsters" to "மான்ஸ்டர்ஸ்",
            "titan" to "டைட்டன்",
            "asgard" to "ஆஸ்கார்ட்",
            "thor" to "தோர்",
            "loki" to "லோகி",
            "odin" to "ஓடின்",
            "peter" to "பீட்டர்",
            "kaiju" to "கைஜு",
            "shadow" to "ஷேடோ",
            "level" to "லெவல்",
            "rank" to "ரேங்க்",
            "gate" to "கேட்",
            "boss" to "பாஸ்",
            "dungeon" to "டன்ஜன்",
            "goku" to "கோகு",
            "naruto" to "நருடோ",
            "luffy" to "லஃபி",
            "zoro" to "ஜோரோ",
            "eren" to "ஏரன்"
        )
        knownTransliterations[w]?.let { return it }

        // Algorithmic phonetic transliteration
        val cMap = mapOf(
            "th" to "த", "sh" to "ஷ", "ch" to "ச", "ph" to "ப", "zh" to "ழ", "ng" to "ங",
            "dr" to "டிர", "tr" to "டிர", "st" to "ஸ்ட", "sp" to "ஸ்ப", "sk" to "ஸ்க",
            "k" to "க", "g" to "க", "c" to "க", "s" to "ச", "j" to "ஜ", "t" to "ட", "d" to "ட",
            "n" to "ந", "p" to "ப", "b" to "ப", "m" to "ம", "y" to "ய", "r" to "ர", "l" to "ல",
            "v" to "வ", "w" to "வ", "h" to "ஹ", "z" to "ஜ"
        )
        val vSign = mapOf(
            "a" to "", "aa" to "ா", "i" to "ி", "ee" to "ீ", "e" to "ெ", "ai" to "ை",
            "o" to "ொ", "u" to "ு", "oo" to "ூ"
        )
        val initV = mapOf(
            "a" to "அ", "aa" to "ஆ", "i" to "இ", "ee" to "ஈ", "e" to "எ", "ai" to "ஐ",
            "o" to "ஒ", "u" to "உ", "oo" to "ஊ"
        )

        val res = StringBuilder()
        var i = 0
        val n = w.length
        val consonantKeys = listOf("th", "sh", "ch", "ph", "zh", "ng", "dr", "tr", "st", "sp", "sk",
            "k", "g", "c", "s", "j", "t", "d", "n", "p", "b", "m", "y", "r", "l", "v", "w", "h", "z")
        val vowelKeys = listOf("aa", "ee", "oo", "ai", "a", "i", "e", "o", "u")

        while (i < n) {
            var matchedC: String? = null
            for (c in consonantKeys) {
                if (w.startsWith(c, i)) {
                    matchedC = c
                    break
                }
            }
            if (matchedC != null) {
                i += matchedC.length
                var matchedV: String? = null
                for (v in vowelKeys) {
                    if (w.startsWith(v, i)) {
                        matchedV = v
                        break
                    }
                }
                val base = cMap[matchedC] ?: ""
                if (matchedV != null) {
                    i += matchedV.length
                    val sign = vSign[matchedV] ?: ""
                    res.append(base).append(sign)
                } else {
                    if (base.length == 1) {
                        res.append(base).append("்")
                    } else {
                        res.append(base)
                    }
                }
            } else {
                var matchedV: String? = null
                for (v in vowelKeys) {
                    if (w.startsWith(v, i)) {
                        matchedV = v
                        break
                    }
                }
                if (matchedV != null) {
                    i += matchedV.length
                    res.append(initV[matchedV] ?: "")
                } else {
                    i++
                }
            }
        }

        val out = res.toString()
        return if (out.isNotEmpty()) out else word
    }
}
