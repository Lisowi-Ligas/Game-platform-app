package models

import utils.Utilities

data class Platform(var platformId: Int = 0,
                    var platformModel: String,
                    var platformTitle: String,
                    var platformCost: String,
                    var platformPopularity: Int,
                    var platformVersion: Int,
                    var isPlatformDiscontinued :Boolean,
                    var games : MutableSet<Game> = mutableSetOf()){
    private var lastGameId = 0
    private fun getGameId() = lastGameId++

    fun addGame(game: Game) : Boolean {
        game.gameId = getGameId()
        return games.add(game)
    }

    fun numberOfGames() = games.size

    fun findOne(id: Int): Game?{
        return games.find{ game -> game.gameId == id }
    }

    fun delete(id: Int): Boolean {
        return games.removeIf { game -> game.gameId == id}
    }

    fun update(id: Int, newGame : Game): Boolean {
        val foundGame = findOne(id)

        //if the object exists, use the details passed in the newItem parameter to
        //update the found object in the Set
        if (foundGame != null){
            foundGame.gameName = newGame.gameName
            foundGame.gameGenre = newGame.gameGenre
            foundGame.gameAgeRating = newGame.gameAgeRating
            foundGame.gameStar = newGame.gameStar
            foundGame.didYouCompleteGame = newGame.didYouCompleteGame
            return true
        }

        //if the object was not found, return false, indicating that the update was not successful
        return false
    }

    fun listGames() =
        if (games.isEmpty())  "\tNO Games ADDED"
        else  Utilities.formatSetString(games)

    override fun toString(): String {
        val discontinued = if (isPlatformDiscontinued) 'Y' else 'N'
        return "$platformId: Model$platformModel, Title($platformTitle), Cost($platformCost), Popularity($platformPopularity), Version($platformVersion), Discontinued($discontinued) \n${listGames()}"
    }

    fun checkPlatformCompletionStatus(): Boolean {
        if (games.isNotEmpty()) {
            for (game in games) {
                if (!game.didYouCompleteGame) {
                    return false
                }
            }
        }
        return true //a platform with empty items can be archived, or all items are complete
    }

}