package models

data class Platform(val platformModel: String,
                    val platformTitle: String,
                    val platformCost: String,
                    val platformPopularity: Int,
                    val platformVersion: Int,
                    val isPlatformDiscontinued :Boolean){
}