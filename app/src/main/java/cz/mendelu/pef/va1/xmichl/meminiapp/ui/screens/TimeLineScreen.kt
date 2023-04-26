package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.runtime.Composable
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.NavScreen

@Composable
fun TimeLineScreen(navigation: INavigationRouter) {
    NavScreen(
        appBarTitle = "My memories",
        onBackClick = {},
        fullScreenContent = false,
        destination = Destination.TimeLineScreen,
        navigation = navigation
    ) {
        //Greeting(name = "Android")
    }
}