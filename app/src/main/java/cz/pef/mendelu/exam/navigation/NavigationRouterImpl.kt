package cz.pef.mendelu.exam.navigation

import androidx.navigation.NavController

class NavigationRouterImpl(private val navController: NavController) : INavigationRouter {

    override fun getNavController(): NavController = navController

    override fun returnBack() {
        navController.popBackStack()
    }

    override fun navigateToAddCounterScreen(id: Long?) {
        navController.navigate(
            Destination.AddCounterScreen.route + "/" + id)
    }

}
