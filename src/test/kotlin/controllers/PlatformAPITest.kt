package controllers

import models.Platform
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class PlatformAPITest {

    private var Windows: Platform? = null
    private var Linux: Platform? = null
    private var Mac: Platform? = null
    private var Xbox: Platform? = null
    private var Playstation: Platform? = null
    private var populatedPlatforms: PlatformAPI? = PlatformAPI()
    private var emptyPlatforms: PlatformAPI? = PlatformAPI()

    @BeforeEach
    fun setup(){
        Windows = Platform("Windows", "PC", "1200 Euro", 7,10,false)
        Linux = Platform("Linux", "PC", "1200 Euro",5,9, false)
        Mac = Platform("Mac", "Apple", "2000 Euro", 6, 1,false)
        Xbox = Platform("Xbox", "XboxSeriesX", "500 Euro", 7,5,false)
        Playstation = Platform("Playstation", "5", "500", 7,5,false)

        //adding 5 Note to the notes api
        populatedPlatforms!!.add(Windows!!)
        populatedPlatforms!!.add(Linux!!)
        populatedPlatforms!!.add(Mac!!)
        populatedPlatforms!!.add(Xbox!!)
        populatedPlatforms!!.add(Playstation!!)
    }

    @AfterEach
    fun tearDown(){
        Windows = null
        Linux = null
        Mac = null
        Xbox = null
        Playstation = null
        populatedPlatforms = null
        emptyPlatforms = null
    }

    @Test
    fun `adding a Platform to a populated list adds to ArrayList`(){
        val newPlatform = Platform("NintendoSwitch", "NintendoSwitch", "200 Euro", 3,5,false)
        assertEquals(5, populatedPlatforms!!.numberOfPlatforms())
        assertTrue(populatedPlatforms!!.add(newPlatform))
        assertEquals(6, populatedPlatforms!!.numberOfPlatforms())
        assertEquals(newPlatform, populatedPlatforms!!.findPlatform(populatedPlatforms!!.numberOfPlatforms() - 1))
    }

    @Test
    fun `adding a Platform to an empty list adds to ArrayList`(){
        val newPlatform = Platform("NintendoSwitch", "NintendoSwitch", "200 Euro", 3,5,false)
        assertEquals(0, emptyPlatforms!!.numberOfPlatforms())
        assertTrue(emptyPlatforms!!.add(newPlatform))
        assertEquals(1, emptyPlatforms!!.numberOfPlatforms())
        assertEquals(newPlatform, emptyPlatforms!!.findPlatform(emptyPlatforms!!.numberOfPlatforms() - 1))
    }

    @Test
    fun `listAllPlatforms returns No Platforms Stored message when ArrayList is empty`() {
        assertEquals(0, emptyPlatforms!!.numberOfPlatforms())
        assertTrue(emptyPlatforms!!.listAllPlatforms().lowercase().contains("no platforms"))
    }

    @Test
    fun `listAllPlatforms returns Notes when ArrayList has notes stored`() {
        assertEquals(5, populatedPlatforms!!.numberOfPlatforms())
        val platformsString = populatedPlatforms!!.listAllPlatforms().lowercase()
        assertFalse(platformsString.contains("Windows"))
        assertFalse(platformsString.contains("Linux"))
        assertFalse(platformsString.contains("Mac"))
        assertFalse(platformsString.contains("Xbox"))
        assertFalse(platformsString.contains("Playstation"))
    }

    @Test
    fun `listActivePlatforms returns no active platforms stored when ArrayList is empty`() {
        assertEquals(0, emptyPlatforms!!.numberOfActivePlatforms())
        assertTrue(
            emptyPlatforms!!.listActivePlatforms().lowercase().contains("no active platforms")
        )
    }

    @Test
    fun `listActivePlatforms returns active platforms when ArrayList has active platforms stored`() {
        assertEquals(3, populatedPlatforms!!.numberOfActivePlatforms())
        val activePlatformsString = populatedPlatforms!!.listActivePlatforms().lowercase()
        assertTrue(activePlatformsString.contains("Windows"))
        assertFalse(activePlatformsString.contains("Linux"))
        assertTrue(activePlatformsString.contains("Mac"))
        assertTrue(activePlatformsString.contains("Xbox"))
        assertFalse(activePlatformsString.contains("Playstation"))
    }

    @Test
    fun `listDiscontinuedPlatforms returns no discontinued platforms when ArrayList is empty`() {
        assertEquals(0, emptyPlatforms!!.numberOfDiscontinuedPlatforms())
        assertTrue(
            emptyPlatforms!!.listDiscontinuedPlatforms().lowercase().contains("no discontinued platforms")
        )
    }

    @Test
    fun `listDiscontinuedPlatforms returns discontinued platforms when ArrayList has discontinued platforms stored`() {
        assertEquals(2, populatedPlatforms!!.numberOfDiscontinuedPlatforms())
        val discontinuedPlatformsString = populatedPlatforms!!.listDiscontinuedPlatforms().lowercase(Locale.getDefault())
        assertFalse(discontinuedPlatformsString.contains("Windows"))
        assertTrue(discontinuedPlatformsString.contains("Linux"))
        assertFalse(discontinuedPlatformsString.contains("Mac"))
        assertFalse(discontinuedPlatformsString.contains("Xbox"))
        assertTrue(discontinuedPlatformsString.contains("Playstation"))
    }

}