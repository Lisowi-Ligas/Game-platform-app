import java.lang.System.exit
import java.util.*


val scanner = Scanner(System.`in`)

fun mainMenu() : Int {
    println("")
    println("--------------------")
    println("PLATFORM KEEPER APP")
    println("--------------------")
    println("PLATFORM MENU")
    println("  1) Add a platform")
    println("  2) List all platforms")
    println("  3) Update a platform")
    println("  4) Delete a platform")
    println("--------------------")
    println("  0) Exit")
    println("--------------------")
    print("==>> ")
    return scanner.nextInt()
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
            else -> println("Invalid option entered: " + option)
        }
    } while (true)
}

fun addPlatform(){
    println("You chose Add Platform")
}

fun listPlatforms(){
    println("You chose List Platforms")
}

fun updatePlatform(){
    println("You chose Update Platform")
}

fun deletePlatform(){
    println("You chose Delete Platform")
}

fun exitApp(){
    println("Stay safe & Don't die")
    exit(0)
}

fun main(args: Array<String>) {
    runMenu()
}
