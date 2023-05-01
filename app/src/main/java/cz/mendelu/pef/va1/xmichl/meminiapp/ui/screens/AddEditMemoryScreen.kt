package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.app.DatePickerDialog
import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notes
import androidx.compose.material.icons.filled.Title
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.InfoElement
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.MyTextfield
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.NavScreen
import cz.mendelu.pef.va1.xmichl.meminiapp.utils.DateUtils
import org.koin.androidx.compose.getViewModel
import java.util.*

@Composable
fun AddEditMemoryScreen(
    navigation: INavigationRouter,
    viewModel: AddEditMemoryViewModel = getViewModel(),
    id: Long? = null //TODO change this to be mandatory
) {

    viewModel.memoryId = id

    var data: AddEditScreenData by remember {
        mutableStateOf(viewModel.data)
    }


    viewModel.addEditMemoryUIState.value.let {
        when (it) {
            AddEditScreenUIState.Loading -> {
                viewModel.initMemory()
            }
            AddEditScreenUIState.Default -> {}
            AddEditScreenUIState.Loading -> TODO()
            AddEditScreenUIState.MemoryChanged -> {
                data = viewModel.data
                viewModel.addEditMemoryUIState.value = AddEditScreenUIState.Default
            }
            AddEditScreenUIState.MemorySaved -> {
                LaunchedEffect(it) {
                    navigation.returnBack()
                }
            }
        }
    }


    NavScreen(
        appBarTitle = "Add Edit", //TODO: memory title
        destination = Destination.AddEditMemoryScreen, //TODO: prev or prevprev destination
        navigation = navigation,
        backArrowNeeded = true,
        onBackClick = {
            navigation.returnBack()
        }
    ) {
        AddEditScreenContent(
            actions = viewModel,
            data = data
        )
    }
}

@Composable
fun AddEditScreenContent(
    actions: AddEditMemoryActions,
    data: AddEditScreenData

) {
    if (!data.loading) {
        Column(
            modifier = Modifier.fillMaxSize(0.9f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                Box(modifier = Modifier.fillMaxSize(0.25f)) {
                    Image(
                        painter = painterResource(id = R.drawable.photo_place_holder),
                        contentDescription = null
                    )
                }
                Box(modifier = Modifier.fillMaxSize(0.33f)) {
                    Image(
                        painter = painterResource(id = R.drawable.photo_place_holder),
                        contentDescription = null
                    )
                }
                Box(modifier = Modifier.fillMaxSize(0.5f)) {
                    Image(
                        painter = painterResource(id = R.drawable.photo_place_holder),
                        contentDescription = null
                    )
                }
            }


            //Spacer(modifier = Modifier.height(20.dp))
            //Divider(thickness = 2.dp)
            Spacer(modifier = Modifier.height(50.dp))
            MyTextfield(
                value = data.memory.title,
                onValueChange = {
                    actions.onTitleChanged(it)
                },
                leadingIcon = Icons.Default.Title,
                label = "Title",
                onClearClick = {
                    actions.onTitleChanged("")
//                    data.memory.title = ""
                }
            )

            val calendar = Calendar.getInstance()
            data.memory.date.let {
                calendar.timeInMillis = it
            }

            val y = calendar.get(Calendar.YEAR)
            val m = calendar.get(Calendar.MONTH)
            val d = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                LocalContext.current,
                { dialog: DatePicker, year: Int, month: Int, day: Int ->
                    actions.onDateChanged(DateUtils.getUnixTime(year, month, day))
                },
                y,
                m,
                d

            )

            InfoElement(
                value = DateUtils.getDateString(data.memory.date),
                label = stringResource(R.string.date),
                leadingIcon = Icons.Default.Today,
                onClick = {
                    datePickerDialog.show()
                },
                showClearIcon = false
            )

            MyTextfield(
                value = "",
                onValueChange = {},
                leadingIcon = Icons.Default.LocationOn,
                label = "Place",
                onClearClick = {}
            )

            MyTextfield(
                value = if (data.memory.description != null)
                    data.memory.description!! else "",
                onValueChange = {
                    actions.onDescriptionChanged(it)
                },
                leadingIcon = Icons.Default.Notes,
                label = "Description",
                onClearClick = {
//                    data.memory.description = null
                    actions.onDescriptionChanged(null)
                }
            )

            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = { actions.saveMemory() }
            ) {
                Text(text = "Save")
            }
        }
    } else {
        // todo loading screen
    }

}