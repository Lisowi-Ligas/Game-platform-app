import controllers.PlatformAPI
import models.Platform
import mu.KotlinLogging
import persistence.XMLSerializer
import utils.ScannerInput
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import java.io.File
import java.lang.System.exit

private val platformAPI = PlatformAPI(XMLSerializer(File("platforms.xml")))

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
    println(platformAPI.listAllPlatforms())
}

fun updatePlatform() {
    //logger.info { "updatePlatforms() function invoked" }
    listPlatforms()
    if (platformAPI.numberOfPlatforms() > 0) {
        //only ask the user to choose the platform if notes exist
        val indexToUpdate = readNextInt("Enter the index of the platform to update: ")
        if (platformAPI.isValidIndex(indexToUpdate)) {
            val platformModel = readNextLine("Enter a model for the platform: ")
            val platformTitle = readNextLine("Enter a title for the platform: ")
            val platformCost = readNextLine("Enter a cost for the platform: ")
            val platformPopularity = readNextInt("Enter a popularity for the platform(1 low -10 high): ")
            val platformVersion = readNextInt("Enter a version for the platform: ")

            //pass the index of the note and the new note details to NoteAPI for updating and check for success.
            if (platformAPI.updatePlatform(indexToUpdate, Platform(platformModel, platformTitle, platformCost, platformPopularity, platformVersion, false ))){
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("There are no platforms for this index number")
        }
    }
}

fun deletePlatform(){
    //logger.info { "deletePlatforms() function invoked" }
    listPlatforms()
    if (platformAPI.numberOfPlatforms() > 0) {
        //only ask the user to choose the platform to delete if platforms exist
        val indexToDelete = readNextInt("Enter the index of the platform to delete: ")
        //pass the index of the platform to PlatformAPI for deleting and check for success.
        val platformToDelete = platformAPI.deletePlatform(indexToDelete)
        if (platformToDelete != null) {
            println("Delete Successful! Deleted platform: ${platformToDelete.platformTitle}")
        } else {
            println("Delete NOT Successful")
        }
    }
}

fun exitApp(){
    println("Stay safe & Don't die")
    exit(0)
}

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    runMenu()
}

fun save() {
    try {
        platformAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

fun load() {
    try {
        platformAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}
