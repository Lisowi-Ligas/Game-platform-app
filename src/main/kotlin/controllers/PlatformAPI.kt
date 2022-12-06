package controllers

import models.Platform
import persistence.Serializer

class PlatformAPI(serializerType: Serializer){

    private var serializer: Serializer = serializerType
    private var platforms = ArrayList<Platform>()


    fun add(platform: Platform): Boolean {
        return platforms.add(platform)
    }

    fun listAllPlatforms(): String {
        return if (platforms.isEmpty()) {
            "No platforms stored"
        } else {
            var listOfPlatforms = ""
            for (i in platforms.indices) {
                listOfPlatforms += "${i}: ${platforms[i]} \n"
            }
            listOfPlatforms
        }
    }

    fun numberOfPlatforms(): Int {
        return platforms.size
    }

    fun findPlatform(index: Int): Platform? {
        return if (isValidListIndex(index, platforms)) {
            platforms[index]
        } else null
    }

    //utility method to determine if an index is valid in a list.
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    fun listActivePlatforms(): String {
        return if (numberOfActivePlatforms() == 0) {
            "No active platforms stored"
        } else {
            var listOfActivePlatforms = ""
            for (platform in platforms) {
                if (!platform.isPlatformDiscontinued) {
                    listOfActivePlatforms += "${platforms.indexOf(platform)}: $platform \n"
                }
            }
            listOfActivePlatforms
        }
    }

    fun listDiscontinuedPlatforms(): String {
        return if (numberOfDiscontinuedPlatforms() == 0) {
            "No archived notes stored"
        } else {
            var listOfDiscontinuedPlatforms = ""
            for (platform in platforms) {
                if (platform.isPlatformDiscontinued) {
                    listOfDiscontinuedPlatforms += "${platforms.indexOf(platform)}: $platform \n"
                }
            }
            listOfDiscontinuedPlatforms
        }
    }

    fun numberOfDiscontinuedPlatforms(): Int {
        var counter = 0
        for (platform in platforms) {
            if (platform.isPlatformDiscontinued) {
                counter++
            }
        }
        return counter
    }

    fun numberOfActivePlatforms(): Int {
        var counter = 0
        for (platform in platforms) {
            if (!platform.isPlatformDiscontinued) {
                counter++
            }
        }
        return counter
    }

    fun listPlatformsBySelectedPopularity(popularity: Int): String {
        return if (platforms.isEmpty()) {
            "No platforms stored"
        } else {
            var listOfPlatforms = ""
            for (i in platforms.indices) {
                if (platforms[i].platformPopularity == popularity) {
                    listOfPlatforms +=
                        """$i: ${platforms[i]}
                        """.trimIndent()
                }
            }
            if (listOfPlatforms.equals("")) {
                "No platforms with popularity: $popularity"
            } else {
                "${numberOfPlatformsByPopularity(popularity)} platforms with popularity $popularity: $listOfPlatforms"
            }
        }
    }

    fun numberOfPlatformsByPopularity(popularity: Int): Int {
        var counter = 0
        for (platform in platforms) {
            if (platform.platformPopularity == popularity) {
                counter++
            }
        }
        return counter
    }

    fun deletePlatform(indexToDelete: Int): Platform? {
        return if (isValidListIndex(indexToDelete, platforms)) {
            platforms.removeAt(indexToDelete)
        } else null
    }

    fun updatePlatform(indexToUpdate: Int, platform: Platform?): Boolean {
        //find the platform object by the index number
        val foundPlatform = findPlatform(indexToUpdate)

        //if the platform exists, use the platform details passed as parameters to update the found platform in the ArrayList.
        if ((foundPlatform != null) && (platform != null)) {
            foundPlatform.platformModel = platform.platformModel
            foundPlatform.platformTitle = platform.platformTitle
            foundPlatform.platformCost = platform.platformCost
            foundPlatform.platformPopularity = platform.platformPopularity
            foundPlatform.platformVersion = platform.platformVersion
            return true
        }

        //if the platform was not found, return false, indicating that the update was not successful
        return false
    }

    fun isValidIndex(index: Int) :Boolean{
        return isValidListIndex(index, platforms);
    }

    @Throws(Exception::class)
    fun load() {
        platforms = serializer.read() as ArrayList<Platform>
    }

    @Throws(Exception::class)
    fun store() {
        serializer.write(platforms)
    }

    fun archivePlatform(indexToArchive: Int): Boolean {
        if (isValidIndex(indexToArchive)) {
            val platformToArchive = platforms[indexToArchive]
            if (!platformToArchive.isPlatformDiscontinued) {
                platformToArchive.isPlatformDiscontinued = true
                return true
            }
        }
        return false
    }

}