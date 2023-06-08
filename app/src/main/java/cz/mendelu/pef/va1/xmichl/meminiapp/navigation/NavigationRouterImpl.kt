package cz.mendelu.pef.va1.xmichl.meminiapp.navigation

import androidx.navigation.NavController
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Location

class NavigationRouterImpl(
    private val navController: NavController
) : INavigationRouter {

    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToSearchScreen() {
        navController.navigate(
            Destination.SearchScreen.route
        )
    }

    override fun navigateToTimeLineScreen() {
        navController.navigate(
            Destination.TimeLineScreen.route
        )
    }

    override fun navigateToTodayScreen() {
        navController.navigate(
            Destination.TodayScreen.route
        )
    }

    override fun navigateToSettingsScreen() {
        navController.navigate(
            Destination.SettingsScreen.route
        )
    }

    override fun navigateAddEditMemoryScreen(id: Long?) {
        navController.navigate(
            Destination.AddEditMemoryScreen.route + "/" + id
        )
    }

    override fun navigateToMemoryDetailScreen(id: Long) {
        navController.navigate(Destination.MemoryDetailScreen.route + "/" + id)
    }

    override fun navigateToMapPickerScreen(latitude: Double?, longitude: Double?) {
        if (latitude != null && longitude != null) {
            val moshi: Moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<Location> =
                moshi.adapter(Location::class.java)
            val jsonString = jsonAdapter.toJson(Location(latitude, longitude))

            navController.navigate(Destination.MapPickerScreen.route + "/" + jsonString)
        } else {
            navController.navigate(Destination.MapPickerScreen.route)
        }

    }

    override fun returnFromMap(latitude: Double, longitude: Double) {
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<Location> =
            moshi.adapter(Location::class.java)
        val jsonString = jsonAdapter.toJson(Location(latitude, longitude))

        navController.previousBackStackEntry
            ?.savedStateHandle?.set("location", jsonString)

        returnBack()

    }

    override fun getNavController():
            NavController = navController
}
