package cz.mendelu.pef.va1.xmichl.golf.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cz.mendelu.pef.va1.xmichl.golf.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.golf.ui.elements.BackArrowScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun AddScreen(navigation: INavigationRouter,
              viewModel: AddViewModel = getViewModel()) {

        viewModel.addUIState.value.let {
            when(it) {
                AddUIState.Default -> {

                }
                AddUIState.GolfistSaved -> {
                    LaunchedEffect(it) {
                        navigation.returnBack()
                    }
                }
            }
        }

    BackArrowScreen(
        appBarTitle = "Add item",
        onBackClick = { navigation.returnBack() },
        fullScreenContent = false
    ) {
        AddScreenContent(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddScreenContent(actions: AddActions) {
    var name = remember { mutableStateOf("") }
    var score = remember { mutableStateOf("") }

    TextField(value = name.value, label = { Text("Player name") }, onValueChange = {
        name.value = it
    })

    TextField(value = score.value, label = { Text("Score") }, onValueChange = {
        score.value = it
    })


    OutlinedButton(onClick = {
        actions.saveGolfist(name = name.toString(), score = score.toString())

    }) {
        Text(text = "Save")
    }
}