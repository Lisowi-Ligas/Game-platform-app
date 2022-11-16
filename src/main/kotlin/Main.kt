import mu.KotlinLogging
import utils.ScannerInput
import java.lang.System.exit

fun mainMenu() : Int {
    return ScannerInput.readNextInt(""" 
         > ----------------------------------
         > |        PLATFORM GAME APP         |
         > ----------------------------------
         > | PLATFORM MENU                      |
         > |   1) Add a platform                |
         > |   2) List all platforms            |
         > |   3) Update a platform             |
         > |   4) Delete a platform             |
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
    logger.info { "addPlatform() function invoked" }
}

fun listPlatforms(){
    logger.info { "listPlatforms() function invoked" }
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
