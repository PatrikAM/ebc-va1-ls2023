package cz.mendelu.pef.va1.xmichl.homework2.navigation

import androidx.navigation.NavController

interface INavigationRouter {
    fun returnBack()
    fun navigateToNewContactScreen()
    fun getNavController(): NavController
}
