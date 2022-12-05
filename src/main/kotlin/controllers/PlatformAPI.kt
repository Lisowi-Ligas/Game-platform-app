package controllers

import models.Platform

class PlatformAPI {
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

}