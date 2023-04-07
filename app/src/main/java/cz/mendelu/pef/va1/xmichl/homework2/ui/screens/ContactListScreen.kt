package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.mendelu.pef.va1.xmichl.homework2.model.Contact
import cz.mendelu.pef.va1.xmichl.homework2.model.ContactType
import cz.mendelu.pef.va1.xmichl.homework2.ui.elements.LetterAvatar
import kotlin.random.Random

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactListScreen() {

    val contacts: List<Contact> = listOf(
        Contact(
            name = "John",
            surname = "Wick",
            phone_number = "+420234234234",
            type = ContactType.WORK,
            email = ""
        ),
        Contact(
            name = "Susan",
            surname = "Doe",
            phone_number = "+420123132123",
            type = ContactType.PERSONAL,
            email = ""
        ),
        )

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(text = "Contacts")
            })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                //navigation.navigateToNewContactScreen()
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
    //navigation: INavigationRouter
) {

    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        contacts.forEach {
            item(key = it.id) {
                ContactRow(contact = it)
            }
        }

    }
}

@Composable
fun ContactRow(contact: Contact) {
    Row {
        LetterAvatar(contact.name.get(0))
        Column {
            Text(text = "${contact.surname}, ${contact.name}")
            Text(text = "${contact.phone_number}")
        }
    }
}
