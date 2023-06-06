package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.AddEditMemoryFAB
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList.MemoryList
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.NavScreen

@Composable
fun TimeLineScreen(
    navigation: INavigationRouter,
) {
    NavScreen(
        appBarTitle = stringResource(R.string.my_memories),
        boxContent = false,
        destination = Destination.TimeLineScreen,
        navigation = navigation,
        floatingActionButton = {
            AddEditMemoryFAB(navigation = navigation)
        }
    ) {
        MemoryList(navigation = navigation)
        //Greeting(name = "Android")
    }
}
