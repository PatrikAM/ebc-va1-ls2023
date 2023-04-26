package cz.mendelu.pef.va1.xmichl.meminiapp.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun returnBack()
    fun navigateToSearchScreen()
    fun navigateToTimeLineScreen()
    fun navigateToTodayScreen()
    fun getNavController(): NavController
}
