package cz.pef.mendelu.exam.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.pef.mendelu.exam.ui.screens.CounterListScreen
import cz.pef.mendelu.exam.ui.screens.AddCounterScreen


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    navigation: INavigationRouter = remember {
        NavigationRouterImpl(navController)
    },
    startDestination: String
) {

    NavHost(
        navController = navController,
        startDestination = startDestination
    )
    {
        composable(Destination.CounterListScreen.route) {
            CounterListScreen(navigation)
        }

        composable(
            route = Destination.AddCounterScreen.route + "/{id}",
            arguments = listOf(
                navArgument("id") {
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ) {
            val id = it.arguments?.getLong("id")
            AddCounterScreen(navigation, id = if (id != -1L) id else null)
        }

    }


}


