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
        assertEquals(5, populatedPlatforms!!.numberOfActivePlatforms())
        val activePlatformsString = populatedPlatforms!!.listActivePlatforms().lowercase()
        assertFalse(activePlatformsString.contains("Windows"))
        assertFalse(activePlatformsString.contains("Linux"))
        assertFalse(activePlatformsString.contains("Mac"))
        assertFalse(activePlatformsString.contains("Xbox"))
        assertFalse(activePlatformsString.contains("Playstation"))
    }

    @Test
    fun `listDiscontinuedPlatforms returns no discontinued platforms when ArrayList is empty`() {
        assertEquals(0, emptyPlatforms!!.numberOfDiscontinuedPlatforms())
        assertFalse(
            emptyPlatforms!!.listDiscontinuedPlatforms().lowercase().contains("no discontinued platforms")
        )
    }

    @Test
    fun `listDiscontinuedPlatforms returns discontinued platforms when ArrayList has discontinued platforms stored`() {
        assertEquals(0, populatedPlatforms!!.numberOfDiscontinuedPlatforms())
        val discontinuedPlatformsString = populatedPlatforms!!.listDiscontinuedPlatforms().lowercase(Locale.getDefault())
        assertFalse(discontinuedPlatformsString.contains("Windows"))
        assertFalse(discontinuedPlatformsString.contains("Linux"))
        assertFalse(discontinuedPlatformsString.contains("Mac"))
        assertFalse(discontinuedPlatformsString.contains("Xbox"))
        assertFalse(discontinuedPlatformsString.contains("Playstation"))
    }

    @Test
    fun `listPlatformsBySelectedPopularity returns No platforms when ArrayList is empty`() {
        assertEquals(0, emptyPlatforms!!.numberOfPlatforms())
        assertTrue(emptyPlatforms!!.listPlatformsBySelectedPopularity(1).lowercase().contains("no platforms")
        )
    }

    @Test
    fun `listPlatformsBySelectedPopularity returns no platforms when no platforms of that popularity exist`() {
        //Popularity 1 (1 platform), 2 (none), 3 (1 platform). 4 (2 platforms), 5 (1 platform)
        assertEquals(5, populatedPlatforms!!.numberOfPlatforms())
        val popularity2String = populatedPlatforms!!.listPlatformsBySelectedPopularity(2).lowercase()
        assertTrue(popularity2String.contains("no platform"))
        assertTrue(popularity2String.contains("2"))
    }

    @Test
    fun `listPlatformsBySelectedPopularity returns all platforms that match that popularity when platforms of that popularity exist`() {
        //Popularity 1 (1 platform), 2 (none), 3 (1 platform). 4 (2 platforms), 5 (1 platform)
        assertEquals(5, populatedPlatforms!!.numberOfPlatforms())
        val popularity1String = populatedPlatforms!!.listPlatformsBySelectedPopularity(1).lowercase()
        assertFalse(popularity1String.contains("1 platform"))
        assertFalse(popularity1String.contains("popularity 1"))
        assertFalse(popularity1String.contains("PC"))
        assertFalse(popularity1String.contains("Linux"))
        assertFalse(popularity1String.contains("Mac"))
        assertFalse(popularity1String.contains("Xbox"))
        assertFalse(popularity1String.contains("Playstation"))


        val popularity4String = populatedPlatforms!!.listPlatformsBySelectedPopularity(4).lowercase(Locale.getDefault())
        assertFalse(popularity4String.contains("2 platform"))
        assertFalse(popularity4String.contains("popularity 4"))
        assertFalse(popularity4String.contains("Xbox"))
        assertFalse(popularity4String.contains("Playstation"))
        assertFalse(popularity4String.contains("Windows"))
        assertFalse(popularity4String.contains("Linux"))
        assertFalse(popularity4String.contains("Mac"))
    }

}