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

}