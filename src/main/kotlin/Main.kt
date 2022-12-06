import controllers.PlatformAPI
import models.Platform
import mu.KotlinLogging
import persistence.JSONSerializer
import persistence.XMLSerializer
import utils.ScannerInput
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import java.io.File
import java.lang.System.exit

//private val platformAPI = PlatformAPI(XMLSerializer(File("platforms.xml")))
private val platformAPI = PlatformAPI(JSONSerializer(File("platforms.json")))

fun mainMenu(): Int {
    return readNextInt(
        """ 
         > ----------------------------------
         > |        Platform APP            |
         > ----------------------------------
         > | Platform MENU                  |
         > |   1) Add a platform            |
         > |   2) List all platforms        |
         > |   3) Update a platform         |
         > |   4) Delete a platform         |
         > |   5) Archive a platform        |
         > ----------------------------------
         > |   20) Save platforms           |
         > |   21) Load platforms           |
         > |   0) Exit                      |
         > ----------------------------------
         > ==>> """.trimMargin(">")
    )
}

fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1 -> addPlatform()
            2 -> listPlatforms()
            3 -> updatePlatform()
            4 -> deletePlatform()
            5 -> archivePlatform()
            20 -> save()
            21 -> load()
            0 -> exitApp()
            else -> System.out.println("Invalid option entered: ${option}")
        }
    } while (true)
}

fun listPlatforms() {
    if (platformAPI.numberOfPlatforms() > 0) {
        val option = readNextInt(
            """
                  > --------------------------------
                  > |   1) View ALL platforms          |
                  > |   2) View ACTIVE platforms       |
                  > |   3) View ARCHIVED platforms     |
                  > --------------------------------
         > ==>> """.trimMargin(">"))

        when (option) {
            1 -> listAllPlatforms();
            2 -> listActivePlatforms();
            3 -> listArchivedPlatforms();
            else -> println("Invalid option entered: " + option);
        }
    } else {
        println("Option Invalid - No platforms stored");
    }
}

fun listAllPlatforms() {
    println(platformAPI.listAllPlatforms())
}

fun listArchivedPlatforms() {
    println(platformAPI.listDiscontinuedPlatforms())
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

fun listActivePlatforms() {
    println(platformAPI.listActivePlatforms())
}

fun archivePlatform() {
    listActivePlatforms()
    if (platformAPI.numberOfActivePlatforms() > 0) {
        //only ask the user to choose the platform to archive if active platforms exist
        val indexToArchive = readNextInt("Enter the index of the platform to archive: ")
        //pass the index of the platform to PlatformAPI for archiving and check for success.
        if (platformAPI.archivePlatform(indexToArchive)) {
            println("Archive Successful!")
        } else {
            println("Archive NOT Successful")
        }
    }
}