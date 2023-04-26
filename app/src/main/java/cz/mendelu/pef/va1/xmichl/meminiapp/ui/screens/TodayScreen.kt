package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.AddEditMemoryFAB
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.NavScreen

@Composable
fun TodayScreen(navigation: INavigationRouter) {
    NavScreen(
        appBarTitle = "On this day",
        onBackClick = {},
        fullScreenContent = false,
        destination = Destination.TodayScreen,
        navigation = navigation,
        floatingActionButton = {
            AddEditMemoryFAB(navigation = navigation)
        }
    ) {
        //Greeting(name = "Android")
    }
}
