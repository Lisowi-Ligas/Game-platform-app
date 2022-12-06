package controllers

import models.Platform
import persistence.Serializer
import utils.Utilities

class PlatformAPI(serializerType: Serializer){

    private var serializer: Serializer = serializerType
    private var platforms = ArrayList<Platform>()


    fun add(platform: Platform): Boolean {
        return platforms.add(platform)
    }

    fun listAllPlatforms(): String =
        if  (platforms.isEmpty()) "No platforms stored"
        else Utilities.formatListString(platforms)

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


    fun listActivePlatforms(): String =
        if  (numberOfActivePlatforms() == 0)  "No active platforms stored"
        else Utilities.formatListString(platforms.filter { platform -> !platform.isPlatformDiscontinued})

    fun listDiscontinuedPlatforms(): String =
        if  (numberOfDiscontinuedPlatforms() == 0) "No archived platforms stored"
        else Utilities.formatListString(platforms.filter { platform -> platform.isPlatformDiscontinued})

    fun numberOfDiscontinuedPlatforms(): Int = platforms.count { platform: Platform -> platform.isPlatformDiscontinued }

    fun numberOfActivePlatforms(): Int = platforms.count { platform: Platform -> !platform.isPlatformDiscontinued }

    fun listPlatformsBySelectedPopularity(popularity: Int): String =
        if (platforms.isEmpty()) "No platforms stored"
        else {
            val listOfPlatforms = Utilities.formatListString(platforms.filter{ platform -> platform.platformPopularity == popularity})
            if (listOfPlatforms.equals("")) "No platforms with popularity: $popularity"
            else "${numberOfPlatformsByPopularity(popularity)} platforms with popularity $popularity: $listOfPlatforms"
        }

    fun numberOfPlatformsByPopularity(popularity: Int): Int = platforms.count { platform: Platform -> platform.platformPopularity == popularity}


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

    fun searchByTitle (searchString : String) =
        Utilities.formatListString(
            platforms.filter { platform -> platform.platformTitle.contains(searchString, ignoreCase = true) })


    fun searchGameByName(searchString: String): String {
        return if (numberOfPlatforms() == 0) "No platforms stored"
        else {
            var listOfPlatforms = ""
            for (platform in platforms) {
                for (game in platform.games) {
                    if (game.gameName.contains(searchString, ignoreCase = true)) {
                        listOfPlatforms += "${platform.platformId}: ${platform.platformTitle} \n\t${game}\n"
                    }
                }
            }
            if (listOfPlatforms == "") "No games found for: $searchString"
            else listOfPlatforms
        }
    }

    fun listUnfinishedGames(): String =
        if (numberOfPlatforms() == 0) "No platforms stored"
        else {
            var listUnfinishedGames = ""
            for (platform in platforms) {
                for (game in platform.games) {
                    if (!game.didYouCompleteGame) {
                        listUnfinishedGames += platform.platformTitle + ": " + game.gameName + "\n"
                    }
                }
            }
            listUnfinishedGames
        }

    fun numberOfUnfinishedGames(): Int {
        var numberOfUnfinishedGames = 0
        for (platform in platforms) {
            for (game in platform.games) {
                if (!game.didYouCompleteGame) {
                    numberOfUnfinishedGames++
                }
            }
        }
        return numberOfUnfinishedGames
    }

}

