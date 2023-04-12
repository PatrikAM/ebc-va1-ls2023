package cz.mendelu.pef.va1.xmichl.homework2.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import cz.mendelu.pef.va1.xmichl.homework2.ui.screens.ContactListScreen
import cz.mendelu.pef.va1.xmichl.homework2.ui.screens.NewContactScreen

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

        composable(Destination.ContactListScreen.route) {
            ContactListScreen(navigation)
        }

        composable(Destination.NewContactScreen.route) {
            NewContactScreen(navigation)
        }
    }
}
