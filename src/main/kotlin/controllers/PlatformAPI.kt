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

}