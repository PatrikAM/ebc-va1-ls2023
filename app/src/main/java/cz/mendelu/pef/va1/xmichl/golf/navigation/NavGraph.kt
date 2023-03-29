package cz.mendelu.pef.va1.xmichl.golf.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.mendelu.pef.va1.xmichl.golf.ui.screens.AddScreen
import cz.mendelu.pef.va1.xmichl.golf.ui.screens.ListResultScreen
import cz.mendelu.pef.va1.xmichl.golf.ui.screens.MainScreen

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

        composable(Destination.MainScreen.route){
            MainScreen(navigation)
        }

        composable(
            route = Destination.AddScreen.route
        ){
            AddScreen(navigation)
        }

        composable(
            route = Destination.ListResultScreen.route,
            arguments = listOf(
                navArgument("id"){
                    type = NavType.LongType
                    defaultValue = -1L
                }
            )
        ){
            ListResultScreen(navigation)
        }



    }




}