package cz.mendelu.pef.va1.xmichl.meminiapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.SearchScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.SplashScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.TimeLineScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens.TodayScreen

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
    }
}
