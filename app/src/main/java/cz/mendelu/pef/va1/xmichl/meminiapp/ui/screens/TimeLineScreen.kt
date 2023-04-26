package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.runtime.Composable
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.NavScreen

@Composable
fun TimeLineScreen() {
    NavScreen(
        appBarTitle = "Time Line",
        onBackClick = {},
        fullScreenContent = false,
        route = "timeline"
    ) {
        //Greeting(name = "Android")
    }
}