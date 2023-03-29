package cz.mendelu.pef.va1.xmichl.golf.navigation

sealed class Destination(val route: String) {
    object MainScreen : Destination(route = "main_screen")
    object AddScreen : Destination(route = "add_screen")
    object ListResultScreen : Destination(route = "list_result_screen")
}
