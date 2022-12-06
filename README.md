# Game-platform-app

01. Adding a Menu
I added: Add a platform, List all platforms, Update platform, Delete platform and Exit for the user to leave.
    1. mainMenu() function
    Added variable val scanner = Scanner(System.'in')
    Added println() to make the menu.
    2. runMenu function
    This function will repeatedly display the above menu
    3. Remaining functions
    Skeleton code for addPlatform(), listPlatforms(), updatePlatforms(), deletePlatforms(), and exitApp.
    4. main() function
    Simply calls the runMenu() function

02. Refactoring Menu Code
    1. Changed the println() for menu

03. Input Mismatch Exception
    1. ScannerInput Utility - created a package called utilis in src/main/kotlin to hold utility classes.

04. Adding Logging Support
    1. Added 2 new dependencies
    kotlin-logging and slf4j-simple in build.gradle.kts
    2. Synced the gradle
    3. added private val logger = kotlinLogging.logger {} to fix any import errors
    4. added logger.info { "addNote() function invoked" } for each addPlatform etc.
    
05. Tagging a Release
    1. Created a new release in my GitHub repo. V1.0
    2. Added a ReadMe file
    3. Added a Wiki for the GitHub

06. Collection for add and list.
    1. Created a new issue for:
    A collection of Platforms needs to be included in the App so that Platforms can be added to it.
    2. Created a second issue:
    JUnit automated testing should be included for all existing code in the app

07. Creating a new branch 'collection-and-model'
    1. Created a new package called Platform
    2. Created a new package called controllers in the src.main.kotlin
    This will declare the collection of Platform
    3. Added the add function
    4. Added the list function
    5. Added a pull request, closed the issue and deleted the branch
    
08. Created a branch for TDD work
    1. Added a new package PlatformAPITest in the src/test/kotlin
    2. Organised the tests output by grouping tests in nested classes
    3. Added a pull request, closed the issue and deleted the branch

09. Added functionality that will list Platforms in the following:
    1. If no active platforms in the ArrayList = No active platforms stored
    2. If no archived platforms in the ArrayList = No archived platforms stored

10. Added functionality that will list Platforms in order of popularity
    1. Added function fun listPlatformsBySelectedPopularity(popularity: Int): String {}
    2. Added fun numberOfPlatformsByPopularity(): Int {}
    3. Created a tagged release for V2.0
    4. Updated the ReadMe and the Wiki

11. Delete a platform 
    1. Created a new issue:
    Delete a Platform from the Platforms Collection
    2. Created a new branch for the delete function issue
    3. PlatformAPI - Added the delete functionality
    4. Updated the code in fun deletePlatform() in Main.kt
    5. JUnit tests
    6. Added a pull request, closed the issue and deleted the branch

12. Update a platform
    1. Created a new issue:
    Update a Platform in the Platforms Collection
    2. Created a new branch for the update functionality
    3. PlatformAPI - Added the update functionality
    4. Updated code fun updatePlatform() in Main.kt
    5. Added fun isValidIndex function to fox any compile errors
    6. JUnit testing for UpdatePlatforms
    7. Added a pull request, closed the issue and deleted the branch

13. New functionality Persistence for XML & JSON
    Persistence - XML
    1. Creating the Branch - Persistence
    2. created a persistence package and added a series of classes and interfaces to it
    src/main/kotlin - inside this created 2 new kotlin classes
    JSONSerializer and XMLSerializer
    3. Added an interface to this package Serializer
    4. updated PlatformAPI to use the new serialization classes and interfaces.

    5. Added gradle dependencies in build.gradle.kts
    implementation("com.thoughtworks.xstream:xstream:1.4.18")
    implementation("org.codehaus.jettison:jettison:1.4.1")
    6. Synced the gradle to download

13. Persistence - JSON
    1. Updated code in JSONSerializer
    2. In PlatformAPI updated code so JSONSerializer will be used:
    private val platformAPI = PlatformAPI(JSONSerializer(File("platforms.json)))
    3. JUnit tests
    4. Added a pull request, closed the issue and deleted the branch

14. Archive a Platform
    1. Created a new issue and a branch
    2. Created a new menu item:
    Archive a Platform
    3. Updated the menu and added the new function to PlatformAPI Main.kt
    4. JUnit tests
    5. Added pull request, closed the issue and deleted the branch

15. List Submenu
    1. Created a new issue and a new branch
    2. Created a menu 2 called Submenu
    2. Updated the main.kt and added new functions:
    fun listAllPlatforms(), fun listArchivedPlatforms()
    3. Updated the ReadMe
    4. Created a new tagged release V3.0
    5. Created a pull request, closed the issue and deleted the branch
    
16. Tracked Files
    1. updated the gitignore folder so it ignores the build folder

17. Lambdas Counting
    1. Created a new issue and a new branch
    2. Refactoring the number of active platforms
    3. Refactoring the number of Discontinued Platforms
    4. Refactoring the number of Platforms by popularity
    5. JUnit tests
    6. Created a pull request, closed the issue and deleted the branch
    
18. Lambdas List all, Listing
    1. Created a new issue and a new branch: Refactoring-lambda-list
    2. JUnit tests
    3. Refactoring list all platforms in the PlatformAPI
    4. Refactoring list discontinued platforms
    5. Refactoring list active Platforms
    6. Created a pull request, closed the issue and deleted the branch
    
19. Lambdas Searching & Refactoring
    1. Created a new issue and a new branch
    2. Added fun searchByTitle() in the platformAPI
    2. Updated the main by adding fun searchPlatforms()
    3. JUnit testing
    4. Refactoring the joinString in my platforms:
    5. fun numberOfDiscontinuedPlatforms, listAllPlatforms, listActivePlatforms, listDiscontinuedPlatforms, listPlatformsBySelectedPopularity
    5. Created a pull request, closed the issue and deleted the branch

