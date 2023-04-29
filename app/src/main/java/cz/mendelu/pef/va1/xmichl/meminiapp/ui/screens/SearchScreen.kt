package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dt.composedatepicker.ComposeCalendar
import com.dt.composedatepicker.SelectDateListener
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.AddEditMemoryFAB
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.InfoElement
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.MyTextfield
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.NavScreen
import java.util.*

@Composable
fun SearchScreen(
    navigation: INavigationRouter
) {

    var isInSelectSate = remember {
        mutableStateOf(false)
    }

    if (!isInSelectSate.value) {
        NavScreen(
            appBarTitle = "Search memories",
            onBackClick = {},
            columnContent = false,
            destination = Destination.SearchScreen,
            navigation = navigation,
            floatingActionButton = {
                AddEditMemoryFAB(navigation = navigation)
            }
        ) {


            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier.fillMaxSize(0.9f)
            ) {
                MyTextfield(
                    value = "",
                    onValueChange = {},
                    leadingIcon = Icons.Default.Title,
                    label = "Memory name",
                )

                InfoElement(
                    value = "",
                    label = "Date",
                    leadingIcon = Icons.Default.CalendarToday,
                    onClick = {
                        isInSelectSate.value = true
                    }) {

                }


            }
            Spacer(modifier = Modifier.height(30.dp))
            Divider(thickness = 2.dp)
        }
    } else {
        Box(
            Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Box(
                Modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.6f),
                //.fillMaxSize(0.9f),
                //.background(color = Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                ComposeCalendar(
                    //maxDate = Date(),
                    //calendarType = CalendarType.MONTH_AND_YEAR,
                    //listener = DataSelection(),
                    //title = "Select Date",
                    listener = object : SelectDateListener {
                        override fun onDateSelected(date: Date) {
                            Log.d("DATE", date.toString())
                            isInSelectSate.value = false
                        }

                        override fun onCanceled() {
                            //setOpen(false)
                            //setOpen(false)
                            isInSelectSate.value = false
                        }
                    }

                )
            }
        }
    }
}

@Composable
fun SelectionForm() {

}

enum class CalendarType {
    ONLY_MONTH,
    ONLY_YEAR,
    MONTH_AND_YEAR,
    ONE_SCREEN_MONTH_AND_YEAR
}

class DataSelection : SelectDateListener {
    override fun onCanceled() {
    }

    override fun onDateSelected(date: Date) {
        Log.d("'''''''date''''''", "onDateSelected: $date")
    }

}