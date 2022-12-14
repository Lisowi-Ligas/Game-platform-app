package models

data class Game (var gameId: Int = 0,
                 var gameName : String,
                 var gameGenre : String,
                 var gameAgeRating : Int,
                 var gameStar : String,
                 var didYouCompleteGame: Boolean = false)

{
    override fun toString() =
        if (didYouCompleteGame)
            "$gameId: $gameName (Complete)"
        else
            "$gameId: $gameName (Unfinished)"

}