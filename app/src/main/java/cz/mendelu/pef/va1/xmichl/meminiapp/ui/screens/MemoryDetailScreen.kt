package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.runtime.Composable
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.NavScreen

@Composable
fun MemoryDetailScreen(
    navigation: INavigationRouter
) {
    NavScreen(
        appBarTitle = "Title", //TODO: title
        destination = Destination.MemoryDetailScreen, //TODO: prev or prevprev destination
        navigation = navigation,
        backArrowNeeded = true,
        onBackClick = {
            navigation.returnBack()
        }
    ) {

    }
}