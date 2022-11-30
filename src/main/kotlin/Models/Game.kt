package models

data class Game(val gameTitle: String,
                val gamePriority: Int,
                val gameCategory: String,
                val isGameArchived :Boolean){
}