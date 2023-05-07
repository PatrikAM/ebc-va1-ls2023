package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.runtime.Composable
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.AddEditMemoryFAB
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList.MemoryList
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.NavScreen

@Composable
fun TodayScreen(navigation: INavigationRouter) {
    NavScreen(
        appBarTitle = "On this day",
        onBackClick = {},
        boxContent = false,
        destination = Destination.TodayScreen,
        navigation = navigation,
        floatingActionButton = {
            AddEditMemoryFAB(navigation = navigation)
        }
    ) {
        MemoryList(today = true)
    }
}
