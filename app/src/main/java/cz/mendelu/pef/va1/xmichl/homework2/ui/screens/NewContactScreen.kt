package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import cz.mendelu.pef.va1.xmichl.homework2.model.ContactType
import cz.mendelu.pef.va1.xmichl.homework2.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.homework2.ui.elements.BackArrowScreen
import cz.mendelu.pef.va1.xmichl.homework2.ui.elements.NewContactTextField

@Composable
fun NewContactScreen(navigation: INavigationRouter) {
    BackArrowScreen(
        appBarTitle = "New Contact",
        onBackClick = {
            navigation.returnBack()
        }
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
    var selectedType by remember { mutableStateOf("") }

    val options = listOf(
        ContactType.PERSONAL.toString(),
        ContactType.WORK.toString()
    )
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
