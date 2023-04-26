package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.runtime.Composable
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.NavScreen

@Composable
fun SearchScreen() {
    NavScreen(
        appBarTitle = "Search memories",
        onBackClick = {},
        fullScreenContent = false,
        route = "search"
    ) {
        //Greeting(name = "Android")
    }
}