package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.runtime.Composable
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.NavScreen

@Composable
fun TodayScreen() {
    NavScreen(
        appBarTitle = "On this day",
        onBackClick = {},
        fullScreenContent = false,
        route = "today"
    ) {
        //Greeting(name = "Android")
    }
}