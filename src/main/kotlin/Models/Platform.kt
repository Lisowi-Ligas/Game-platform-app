package models

data class Platform(var platformModel: String,
                    var platformTitle: String,
                    var platformCost: String,
                    var platformPopularity: Int,
                    var platformVersion: Int,
                    var isPlatformDiscontinued :Boolean){
}