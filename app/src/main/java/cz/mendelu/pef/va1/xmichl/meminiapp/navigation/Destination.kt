package cz.mendelu.pef.va1.xmichl.meminiapp.navigation

sealed class Destination(val route: String) {
    object TimeLineScreen : Destination(route = "timeline")
    object SearchScreen : Destination(route = "search")
    object TodayScreen : Destination(route = "today")
    object SplashScreen : Destination(route = "splash")
    object MemoryDetailScreen : Destination(route = "detail")
    object AddEditMemoryScreen : Destination(route = "addedit")
    object MapPickerScreen : Destination(route = "mappicker")
    object MapScreen : Destination(route = "map")
    object SettingsScreen : Destination(route = "settings")
}
