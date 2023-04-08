package cz.mendelu.pef.va1.xmichl.homework2.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import cz.mendelu.pef.va1.xmichl.homework2.model.Contact
import cz.mendelu.pef.va1.xmichl.homework2.model.ContactType

@Composable
fun ContactForm(
    onSubmit: () -> Unit
) {
    var phoneNumber = remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf("") }

    val options = listOf(
        ContactType.PERSONAL.toString(),
        ContactType.WORK.toString()
    )
    Column {
        //NameTF(contact =)
        //SurnameTF(contact = )
        //PhoneNumberTF(contact = )
        //EmailTF(contact = )
        Button(
            onClick = onSubmit,
        ) {
            Text(text = "Save Contact")
        }
    }
}

@Composable
fun PhoneNumberTF(contact: Contact) {
    NewContactTextField(
        value = contact.phone_number,
        label = "Phone Number",
        onValueChange = {
            //actions.onValueChange(it)
            //phone_number = it
        },
        error = ""
    )
}

@Composable
fun NameTF(contact: Contact) {
    NewContactTextField(
        value = contact.name,
        label = "Name",
        onValueChange = {
            //actions.onValueChange(it)
            //phone_number = it
        },
        error = ""
    )
}

@Composable
fun SurnameTF(contact: Contact) {
    NewContactTextField(
        value = contact.surname,
        label = "Name",
        onValueChange = {
            //actions.onValueChange(it)
            //phone_number = it
        },
        error = ""
    )
}

@Composable
fun EmailTF(contact: Contact) {
    NewContactTextField(
        value = contact.email,
        label = "Name",
        onValueChange = {
            //actions.onValueChange(it)
            //phone_number = it
        },
        error = ""
    )
}
