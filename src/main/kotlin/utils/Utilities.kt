package utils

import models.Game
import models.Platform

object Utilities {

    // NOTE: JvmStatic annotation means that the methods are static i.e. we can call them over the class
    //      name; we don't have to create an object of Utilities to use them.

    @JvmStatic
    fun formatListString(platformsToFormat: List<Platform>): String =
        platformsToFormat
            .joinToString(separator = "\n") { platform ->  "$platform" }

    @JvmStatic
    fun formatSetString(gamesToFormat: Set<Game>): String =
        gamesToFormat
            .joinToString(separator = "\n") { game ->  "\t$game" }

}