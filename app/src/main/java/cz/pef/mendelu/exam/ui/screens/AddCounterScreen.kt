package cz.pef.mendelu.exam.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import cz.pef.mendelu.exam.navigation.INavigationRouter
import cz.pef.mendelu.exam.ui.elements.BackArrowScreen
import cz.pef.mendelu.exam.ui.elements.MyTextField
import org.koin.androidx.compose.getViewModel

@Composable
fun AddCounterScreen(navigation: INavigationRouter,
                     viewModel: AddCounterViewModel = getViewModel(),
                     id: Long?) {

    viewModel.counterId = id


    var data: AddCounterScreenData by remember {
        mutableStateOf(viewModel.data)
    }


    viewModel.addCounterUIState.value.let {
        when(it){
            AddCounterUIState.CounterChanged -> {
                data = viewModel.data
                viewModel.addCounterUIState.value = AddCounterUIState.Default
            }
            AddCounterUIState.CounterSaved -> {
                LaunchedEffect(it) {
                    navigation.returnBack()
                }
            }
            AddCounterUIState.Default -> {

            }
            AddCounterUIState.Loading -> {
                viewModel.initCounter()
            }
            AddCounterUIState.CounterDeleted -> {
                LaunchedEffect(it) {
                    navigation.returnBack()
                }
            }
        }
    }


    BackArrowScreen(
        topBarTitle = "Add",
        actions = {
            if (id != null) {
                IconButton(onClick = {
                    viewModel.deleteCounter()
                }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
            }


        },
        onBackClick = {
            navigation.returnBack()
        })
    {
        AddCounterScreenContent(
            actions = viewModel,
            navigation = navigation,
            data = data,
            viewing = id != null
        )
    }
}

@Composable
fun AddCounterScreenContent(
    actions: AddCounterViewModel,
    navigation: INavigationRouter,
    data: AddCounterScreenData,
    viewing: Boolean
) {

    if (!data.loading) {


        MyTextField(
            value = data.counter.name,
            label = "Counter Name",
            onValueChange = {
                actions.onNameChanged(it)
            },
            enabled = !viewing,
            error = if (data.counterNameError != null)
                stringResource(id = data.counterNameError!!) else ""
        )


        MyTextField(
            value = if (data.counter.currentValue != null) data.counter.currentValue.toString() else "",
            label = "Current Value",
            onValueChange = {
                actions.onCurrentValueChanged(it)
            },
            enabled = !viewing,
            error = if (data.counterCurrentValueError != null)
                stringResource(id = data.counterCurrentValueError!!) else ""
        )

        MyTextField(
            value = if (data.counter.maximumValue != null) data.counter.maximumValue.toString() else "",
            label = "Maximum Value",
            onValueChange = {
                actions.onMaximumValueChanged(it)
            },
            enabled = !viewing,
            error = if (data.counterMaximumValueError != null)
                stringResource(id = data.counterMaximumValueError!!) else ""
        )

        MyTextField(
            value = if (data.counter.minimumValue != null) data.counter.minimumValue.toString() else "",
            label = "Minimum Value",
            onValueChange = {
                actions.onMinimumValueChanged(it)
            },
            enabled = !viewing,
            error = ""
        )

        Button(onClick = { actions.saveCounter() }) {
            Text(text = "Save counter")
        }
    }
}
