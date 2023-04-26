package cz.mendelu.pef.va1.xmichl.meminiapp.navigation

import androidx.navigation.NavController

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

    override fun navigateAddEditMemoryScreen() {
        navController.navigate(
            Destination.AddEditMemoryScreen.route
        )
    }

    override fun navigateToMemoryDetailScreen() {
        TODO("Not yet implemented")
    }

    override fun getNavController():
            NavController = navController
}
