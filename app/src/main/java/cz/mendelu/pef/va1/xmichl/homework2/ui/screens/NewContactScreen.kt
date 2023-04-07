package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.navigation.NavGraph
import cz.mendelu.pef.va1.xmichl.homework2.ui.elements.BackArrowScreen
import cz.mendelu.pef.va1.xmichl.homework2.ui.elements.NewContactTextField

@Composable
fun NewContactScreen() {
    BackArrowScreen(
        appBarTitle = "New Contact",
        onBackClick = { /*TODO*/ }
    ) {
        NewContactScreenContent()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewContactScreenContent(
    //actions: NewContactScreenActions,
    //navigation: NavGraph
) {
    var phoneNumber = remember { mutableStateOf("") }
    Column {
        NewContactTextField(
            value = phoneNumber.value,
            label = "Phone Number",
            onValueChange = {
                //actions.onValueChange(it)
                phoneNumber.value = it
            },
            error = ""
        )
    }

    //Button()
}