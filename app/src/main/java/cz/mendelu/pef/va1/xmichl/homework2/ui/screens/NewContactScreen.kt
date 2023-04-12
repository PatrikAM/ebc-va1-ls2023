package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import cz.mendelu.pef.va1.xmichl.homework2.model.ContactType
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

            }
        }
    }
    BackArrowScreen(
        appBarTitle = "New Contact",
        onBackClick = {
            navigation.returnBack()
        }
    ) {
        NewContactScreenContent(
            navigation = navigation,
            data = data,
            actions = viewModel
        )
    }
}

@Composable
fun NewContactScreenContent(
    navigation: INavigationRouter,
    data: NewContactScreenData,
    actions: NewContactActions
) {
    var phoneNumber = remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("") }

    var validate by remember {
        mutableStateOf(false)
    }

    //validate = false

    val options = listOf(
        ContactType.PERSONAL.toString(),
        ContactType.WORK.toString()
    )
    Column {
        ContactForm(
            onSubmit = {
                if (data.contact.isContactValid()) {
                    actions.saveContact()
                } else {
                    validate = true
                }
            },
            contact = data.contact,
            actions = actions,
            validate = validate
        )

    }
}
