package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Title
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.dt.composedatepicker.ComposeCalendar
import com.dt.composedatepicker.SelectDateListener
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.AddEditMemoryFAB
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.InfoElement
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.MyTextfield
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.memoryList.MemoryList
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.NavScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.utils.DateUtils.Companion.getDateString
import org.koin.androidx.compose.getViewModel
import java.util.Date

@Composable
fun SearchScreen(
    navigation: INavigationRouter,
    viewModel: SearchViewModel = getViewModel()
) {

    var data: SearchData by remember {
        mutableStateOf(viewModel.data)
    }
    viewModel.searchUIState.value.let {
        when (it) {
            SearchUIState.Default -> {}
            SearchUIState.MemoriesSearched -> {
                data = viewModel.data
                viewModel.searchUIState.value = SearchUIState.Default
            }

            SearchUIState.MonthSelected -> {
                data = viewModel.data
                viewModel.searchUIState.value = SearchUIState.Default
            }

            SearchUIState.TitleChanged -> {
                data = viewModel.data
                viewModel.searchUIState.value = SearchUIState.Default
            }
        }
    }

    val isInSelectSate = remember {
        mutableStateOf(false)
    }

    if (!isInSelectSate.value) {
        NavScreen(
            appBarTitle = stringResource(R.string.search_memories),
            //onBackClick = {},
            destination = Destination.SearchScreen,
            navigation = navigation,
            floatingActionButton = {
                AddEditMemoryFAB(navigation = navigation)
            }
        ) {

            Column(
                horizontalAlignment = CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize(0.9f)
            ) {
                MyTextfield(
                    value = if (data.title != null) data.title!! else "",
                    onValueChange = {
                        viewModel.onTitleChanged(it)
                    },
                    singleLine = true,
                    charLimit = 25,
                    leadingIcon = Icons.Default.Title,
                    label = stringResource(R.string.title),
                    onClearClick = {
                        viewModel.onTitleChanged(null)
                    }
                )

                InfoElement(
                    value = if (data.date != null) getDateString(data.date!!) else "",
                    label = stringResource(id = R.string.date),
                    leadingIcon = Icons.Default.CalendarToday,
                    onClick = {
                        isInSelectSate.value = true
                    },
                    onClearClick = {
                        viewModel.onMonthChanged(null)
                    }
                )
                Spacer(modifier = Modifier.height(10.dp))
                Button(onClick = { viewModel.onSearchClick() }) {
                    Text(text = stringResource(R.string.search_title))
                }


            }
            Spacer(modifier = Modifier.height(20.dp))
            Divider(thickness = 2.dp)


            Column(
//                modifier = Modifier
//                    .scrollable(
//                        rememberScrollState(),
//                        orientation = Orientation.Vertical
//                    )
                //.weight(weight =1f, fill = false)
            ) {
                MemoryList(
                    navigation = navigation,
                    memoryFilter =
                    if (data.filterFunction == null) {
                        { false }
                    } else
                        data.filterFunction!!,
                    //TODO: placeholder should be special
                    placeholderImage =
                    if (data.filterFunction == null)
                        R.drawable.search_placeholder
                    else
                        R.drawable.memory_not_found,
                    placeholderFstLine = if (data.filterFunction != null) stringResource(R.string.not_found) else "",
                    placeholderSndLine = ""
                )
            }
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
                contentAlignment = Alignment.Center
            ) {
                ComposeCalendar(
                    listener = object : SelectDateListener {
                        override fun onDateSelected(date: Date) {
                            //Log.d("DATE", date.toString())
                            viewModel.onMonthChanged(date)
                            isInSelectSate.value = false
                        }

                        override fun onCanceled() {
                            isInSelectSate.value = false
                        }
                    }

                )
            }
        }
    }
}
