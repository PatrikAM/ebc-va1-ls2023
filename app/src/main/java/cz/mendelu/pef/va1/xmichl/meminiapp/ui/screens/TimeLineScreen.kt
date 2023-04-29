package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.runtime.Composable
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.AddEditMemoryFAB
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList.MemoryRow
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.NavScreen

@Composable
fun TimeLineScreen(
    navigation: INavigationRouter,
) {
    NavScreen(
        appBarTitle = "My memories",
        onBackClick = {},
        columnContent = false,
        destination = Destination.TimeLineScreen,
        navigation = navigation,
        floatingActionButton = {
            AddEditMemoryFAB(navigation = navigation)
        }
    ) {
        MemoryRow()
        //Greeting(name = "Android")
    }
}
