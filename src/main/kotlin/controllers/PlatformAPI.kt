package controllers

import models.Platform

class PlatformAPI {
    private var platforms = ArrayList<Platform>()


    fun add(platform: Platform): Boolean {
        return platforms.add(platform)
    }

    fun listAllGames(): String {
        return if (platforms.isEmpty()) {
            "No games stored"
        } else {
            var listOfGames = ""
            for (i in platforms.indices) {
                listOfGames += "${i}: ${platforms[i]} \n"
            }
            listOfGames
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