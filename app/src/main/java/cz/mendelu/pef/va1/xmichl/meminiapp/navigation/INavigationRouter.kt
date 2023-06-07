package cz.mendelu.pef.va1.xmichl.meminiapp.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun returnBack()
    fun navigateToSearchScreen()
    fun navigateToTimeLineScreen()
    fun navigateToTodayScreen()
    fun navigateAddEditMemoryScreen(id: Long?)
    fun navigateToMemoryDetailScreen(id: Long)
    fun navigateToMapPickerScreen(latitude: Double?, longitude: Double?)
    fun returnFromMap(latitude: Double, longitude: Double)
    fun getNavController(): NavController
}
