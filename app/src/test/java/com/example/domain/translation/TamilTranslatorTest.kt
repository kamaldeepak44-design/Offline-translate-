package com.example.domain.translation

import com.example.data.model.TamilSpeechStyle
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TamilTranslatorTest {

    @Test
    fun testDontWorryMySwordIsReady() = runBlocking {
        val result = TamilTranslator.translateWithResult("Don't worry, my sword is ready!")
        assertTrue(result.isSuccess)
        assertEquals("கவலைப்படாதே, என் வாள் தயாராக இருக்கிறது!", result.tamilText)
    }

    @Test
    fun testDontWorry() = runBlocking {
        val result = TamilTranslator.translateWithResult("Don't worry.")
        assertTrue(result.isSuccess)
        assertEquals("கவலைப்படாதே.", result.tamilText)
    }

    @Test
    fun testMySwordIsReady() = runBlocking {
        val result = TamilTranslator.translateWithResult("My sword is ready!")
        assertTrue(result.isSuccess)
        assertEquals("என் வாள் தயாராக இருக்கிறது!", result.tamilText)
    }

    @Test
    fun testWeMustProtectTheRealm() = runBlocking {
        val result = TamilTranslator.translateWithResult("We must protect the realm!")
        assertTrue(result.isSuccess)
        assertEquals("நாம் நாட்டை பாதுகாக்க வேண்டும்!", result.tamilText)
    }

    @Test
    fun testLookOutAncientDragonWakingUp() = runBlocking {
        val result = TamilTranslator.translateWithResult("Look out! The ancient dragon is waking up!")
        assertTrue(result.isSuccess)
        assertTrue("Must contain Tamil translation of look out", result.tamilText.contains("கவனமாக இரு!"))
        assertTrue("Must contain dragon in Tamil", result.tamilText.contains("டிராகன்"))
        assertTrue("Must contain waking up in Tamil", result.tamilText.contains("விழித்துக்கொண்டிருக்கிறது!"))
    }

    @Test
    fun testAllTheOtherHuntersHadYourEyesAtFirst() = runBlocking {
        val result = TamilTranslator.translateWithResult("ALL THE OTHER HLUNTERS HAD YOUR EYES AT FIRST.")
        assertTrue(result.isSuccess)
        assertTrue("Must contain hunters in Tamil", result.tamilText.contains("வேட்டைக்காரர்"))
        assertTrue("Must contain eyes in Tamil", result.tamilText.contains("கண்கள்"))
    }

    @Test
    fun testYouCantBelieveItCanYou() = runBlocking {
        val result = TamilTranslator.translateWithResult("YOU CAN'T BELIEVE IT, CAN YOU?")
        assertTrue(result.isSuccess)
        assertTrue("Must contain believe it in Tamil", result.tamilText.contains("நம்ப முடியாது"))
    }

    @Test
    fun testComicPhrasebookFallbackPatterns() {
        val r1 = ComicPhrasebook.translateFallback("Don't worry, my sword is ready!")
        assertEquals("கவலைப்படாதே, என் வாள் தயாராக இருக்கிறது!", r1)

        val r2 = ComicPhrasebook.translateFallback("Don't worry.")
        assertEquals("கவலைப்படாதே.", r2)

        val r3 = ComicPhrasebook.translateFallback("My sword is ready!")
        assertEquals("என் வாள் தயாராக இருக்கிறது!", r3)

        val r4 = ComicPhrasebook.translateFallback("Look out! The ancient dragon is waking up!")
        assertTrue(r4.contains("கவனமாக இரு!"))
        assertTrue(r4.contains("டிராகன்"))
    }

    @Test
    fun testNaturalSpokenStyle() {
        val r1 = ComicPhrasebook.translateFallback("Don't worry, my sword is ready!", TamilSpeechStyle.NATURAL_SPOKEN)
        assertTrue(r1.contains("கவலைப்படாதே"))
        assertTrue(r1.contains("ரெடியா இருக்கு"))
    }

    @Test
    fun testCharacterVocative() = runBlocking {
        val result = TamilTranslator.translateWithResult("Peter, watch out!")
        assertTrue(result.isSuccess)
        assertTrue(result.tamilText.startsWith("பீட்டர், "))
    }

    @Test
    fun testOutputAlwaysContainsTamilCharacters() = runBlocking {
        val r1 = TamilTranslator.translate("Look out! The ancient dragon is waking up!")
        assertTrue("Must have Tamil characters", r1.any { it in '\u0B80'..'\u0BFF' })

        val r2 = TamilTranslator.translate("ALL THE OTHER HLUNTERS HAD YOUR EYES AT FIRST.")
        assertTrue("Must have Tamil characters", r2.any { it in '\u0B80'..'\u0BFF' })

        val r3 = TamilTranslator.translate("YOU CAN'T BELIEVE IT, CAN YOU?")
        assertTrue("Must have Tamil characters", r3.any { it in '\u0B80'..'\u0BFF' })
    }
}
