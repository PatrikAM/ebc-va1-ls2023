package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.runtime.Composable
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.AddEditMemoryFAB
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.NavScreen

@Composable
fun TimeLineScreen(navigation: INavigationRouter) {
    NavScreen(
        appBarTitle = "My memories",
        onBackClick = {},
        fullScreenContent = false,
        destination = Destination.TimeLineScreen,
        navigation = navigation,
        floatingActionButton = {
            AddEditMemoryFAB(navigation = navigation)
        }
    ) {
        //Greeting(name = "Android")
    }
}