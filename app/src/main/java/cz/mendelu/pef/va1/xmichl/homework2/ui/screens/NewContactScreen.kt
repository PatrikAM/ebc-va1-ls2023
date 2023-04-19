package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import cz.mendelu.pef.va1.xmichl.homework2.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.homework2.ui.elements.BackArrowScreen
import cz.mendelu.pef.va1.xmichl.homework2.ui.elements.ContactForm
import org.koin.androidx.compose.getViewModel

@Composable
fun NewContactScreen(
    navigation: INavigationRouter,
    viewModel: NewContactViewModel = getViewModel()
) {

    var data: NewContactScreenData by remember {
        mutableStateOf(viewModel.data)
    }

    var validate: Boolean by remember {
        mutableStateOf(false)
    }

    viewModel.newContactUIState.value.let {
        when (it) {
            NewContactUIState.ContactChanged -> {
                data = viewModel.data
                viewModel.newContactUIState.value = NewContactUIState.Default
            }
            NewContactUIState.ContactSaved -> {
                LaunchedEffect(it) {
                    navigation.returnBack()
                }
            }
            NewContactUIState.Default -> {

            }
            NewContactUIState.ContactWasNotSaved -> {
                validate = true
            }
        }
    }

    BackArrowScreen(
        appBarTitle = "New Contact",
        onBackClick = {
            navigation.returnBack()
        },
        actions = {
            IconButton(onClick = {
//                if (data.contact.isContactValid())
                    viewModel.saveContact()
//                else
//                    validate = true
            }) {
                Icon(
                    imageVector = Icons.Default.Save,
                    contentDescription = "save"
                )
            }
        }
    ) {
        NewContactScreenContent(
            data = data,
            actions = viewModel,
            validate = validate
        )
    }
}

@Composable
fun NewContactScreenContent(
    data: NewContactScreenData,
    actions: NewContactActions,
    validate: Boolean
) {

    Column {
        ContactForm(
            onSubmit = {},
//            onSubmit = {
//                if (data.contact.isContactValid()) {
//                    actions.saveContact()
//                } else {
//                    validate = true
//                }
//            },
            data = data,
            actions = actions,
            validate = validate
        )
    }
}
