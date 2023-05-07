package cz.mendelu.pef.va1.xmichl.meminiapp.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun returnBack()
    fun navigateToSearchScreen()
    fun navigateToTimeLineScreen()
    fun navigateToTodayScreen()
    fun navigateAddEditMemoryScreen()
    fun navigateToMemoryDetailScreen()
    fun navigateToMapScreen(latitude: Double?, longitude: Double?)
    fun returnFromMap(latitude: Double, longitude: Double)
    fun getNavController(): NavController
}
