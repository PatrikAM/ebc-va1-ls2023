package cz.mendelu.pef.va1.xmichl.homework2.navigation

import androidx.navigation.NavController

class NavigationRouterImpl(
    private val navController: NavController
) : INavigationRouter {

    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToNewContactScreen() {
        navController.navigate(
            Destination.NewContactScreen.route
        )
    }

    override fun getNavController():
            NavController = navController
}
