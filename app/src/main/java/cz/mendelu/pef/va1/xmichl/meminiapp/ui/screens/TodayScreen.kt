package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.AddEditMemoryFAB
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList.MemoryList
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.NavScreen
import java.util.Calendar

@Composable
fun TodayScreen(navigation: INavigationRouter) {
    NavScreen(
        appBarTitle = stringResource(R.string.on_this_day),
        onBackClick = {},
        boxContent = false,
        destination = Destination.TodayScreen,
        navigation = navigation,
        floatingActionButton = {
            AddEditMemoryFAB(navigation = navigation)
        }
    ) {
        MemoryList(
            navigation = navigation,
            memoryFilter = { memory ->
                val calendar = Calendar.getInstance().apply { timeInMillis = memory.date }
                val today = Calendar.getInstance().apply { set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0); set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0) }
                Calendar.getInstance().apply { set(Calendar.HOUR_OF_DAY, 0); set(Calendar.MINUTE, 0); set(Calendar.SECOND, 0); set(Calendar.MILLISECOND, 0) }
                calendar.get(Calendar.MONTH) == today.get(Calendar.MONTH) && calendar.get(Calendar.DAY_OF_YEAR) == today.get(Calendar.DAY_OF_YEAR)
                //memory.date == System.currentTimeMillis()
            }
        )
    }
}
