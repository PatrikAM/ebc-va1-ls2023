package cz.mendelu.pef.va1.xmichl.golf.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun returnBack()
    fun navigateToAddScreen()
    fun navigateToListResultScreen()
    fun getNavController(): NavController
}