package cz.pef.mendelu.exam.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun returnBack()
    fun navigateToAddCounterScreen(id: Long?)
    fun getNavController(): NavController
}