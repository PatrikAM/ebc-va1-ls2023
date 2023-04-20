package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.mendelu.pef.va1.xmichl.homework2.R
import cz.mendelu.pef.va1.xmichl.homework2.model.Contact
import cz.mendelu.pef.va1.xmichl.homework2.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.homework2.ui.elements.ContactRow
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen(
    navigation: INavigationRouter,
    viewModel: ContactListViewModel = getViewModel()
) {

    val contacts = remember { mutableStateListOf<Contact>() }

    viewModel.contactListUIState.value.let {
        when (it) {
            ContactListUIState.Default -> {
                viewModel.loadContacts()
            }
            is ContactListUIState.Success -> {
                contacts.clear()
                contacts.addAll(it.contacts.sortedBy { contact -> contact.surname })
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(R.string.contactListScreenTitle))
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navigation.navigateToNewContactScreen()
                }) {
                Icon(Icons.Filled.Add, contentDescription = "Add")
            }
        }
    ) {
        ContactListScreenContent(
            paddingValues = it,
            contacts = contacts
            //navigation = navigation
        )
    }

}

@Composable
fun ContactListScreenContent(
    paddingValues: PaddingValues,
    contacts: List<Contact>
) {

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        contacts.forEach {
            item(key = it.id) {
                ContactRow(contact = it)
            }
        }
    }
}
