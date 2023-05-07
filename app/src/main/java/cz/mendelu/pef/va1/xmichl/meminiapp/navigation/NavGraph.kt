package cz.mendelu.pef.va1.xmichl.meminiapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Location
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.*

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    navigation: INavigationRouter = remember {
        NavigationRouterImpl(navController)
    },
    startDestination: String
){

    NavHost(navController = navController,
        startDestination = startDestination){

        composable(Destination.TimeLineScreen.route) {
            TimeLineScreen(navigation)
        }

        composable(Destination.TodayScreen.route) {
            TodayScreen(navigation)
        }

        composable(Destination.SearchScreen.route) {
            SearchScreen(navigation)
        }

        composable(Destination.SplashScreen.route) {
            SplashScreen(navigation)
        }

        composable(Destination.AddEditMemoryScreen.route) {
            AddEditMemoryScreen(navigation)
        }

        composable(route = Destination.MapScreen.route) {
            MapScreen(navigation = navigation, latitude = null, longitude = null)
        }

        composable(route = Destination.MapScreen.route + "/{location}",
            arguments = listOf(
                navArgument("location"){
                    type = NavType.StringType
                    defaultValue = ""
                }
            )) {
            val jsonString = it.arguments?.getString("location")
            if (!jsonString.isNullOrEmpty()){
                val moshi: Moshi = Moshi.Builder().build()
                val jsonAdapter: JsonAdapter<Location> =
                    moshi.adapter(Location::class.java)
                val location: Location? = jsonAdapter.fromJson(jsonString)
                MapScreen(
                    navigation = navigation,
                    latitude = location!!.latitude,
                    longitude = location.longitude
                )

            } else {
                MapScreen(navigation = navigation, latitude = null, longitude = null)
            }
        }

    }
}
