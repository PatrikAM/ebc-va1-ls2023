package cz.mendelu.pef.va1.xmichl.golf.navigation

import androidx.navigation.NavController

class NavigationRouterImpl(
    private val navController: NavController) : INavigationRouter {

    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToAddScreen() {
        navController.navigate(
            Destination.AddScreen.route)
    }

    override fun navigateToListResultScreen() {
        navController.navigate(
            Destination.ListResultScreen.route)
    }

    override fun getNavController():
            NavController = navController
}