import controllers.PlatformAPI
import models.Platform
import mu.KotlinLogging
import utils.ScannerInput
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import java.lang.System.exit

fun mainMenu() : Int {
    return ScannerInput.readNextInt(""" 
         > ----------------------------------
         > |        PLATFORM GAME APP       |
         > ----------------------------------
         > | PLATFORM MENU                  |
         > |   1) Add a platform            |
         > |   2) List all platforms        |
         > |   3) Update a platform         |
         > |   4) Delete a platform         |
         > ----------------------------------
         > |   0) Exit                      |
         > ----------------------------------
         > ==>> """.trimMargin(">"))
}

fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1  -> addPlatform()
            2  -> listPlatforms()
            3  -> updatePlatform()
            4  -> deletePlatform()
            0  -> exitApp()
            else -> println("Invalid option entered: ${option}")
        }
    } while (true)
}

fun addPlatform(){
    //logger.info { "addNote() function invoked" }
    val platformModel = readNextLine("Enter a model for the platform: ")
    val platformTitle = readNextLine("Enter a Title for the platform: ")
    val platformCost = readNextLine("Enter a cost for the platform: ")
    val platformPopularity = readNextInt("Enter a popularity (1-low, 2, 3, 4, 5, 6, 7, 8, 9, 10-high): ")
    val platformVersion = readNextInt("Enter a version for the platform: ")
    val isAdded = platformAPI.add(Platform(platformModel, platformTitle, platformCost, platformPopularity, platformVersion, false))

    if (isAdded) {
        println("Added Successfully")
    } else {
        println("Add Failed")
    }
}

fun listPlatforms(){
    //logger.info { "listNotes() function invoked" }
    println(platformAPI.listAllGames())
}

fun updatePlatform(){
    logger.info { "updatePlatform() function invoked" }
}

fun deletePlatform(){
    logger.info { "deletePlatform() function invoked" }
}

fun exitApp(){
    println("Stay safe & Don't die")
    exit(0)
}

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    runMenu()
}

private val platformAPI = PlatformAPI()
