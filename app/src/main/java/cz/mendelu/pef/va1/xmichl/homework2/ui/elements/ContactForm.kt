package cz.mendelu.pef.va1.xmichl.homework2.ui.elements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.va1.xmichl.homework2.model.Contact
import cz.mendelu.pef.va1.xmichl.homework2.model.ContactType
import cz.mendelu.pef.va1.xmichl.homework2.ui.screens.NewContactActions


@Composable
fun ContactForm(
    onSubmit: () -> Unit,
    contact: Contact,
    actions: NewContactActions,
    validate: Boolean = false
) {
    var selectedType by remember { mutableStateOf("") }

    val options = listOf(
        ContactType.PERSONAL.toString(),
        ContactType.WORK.toString()
    )

    Column {
        NameTF(contact = contact, actions = actions, validate = validate)
        SurnameTF(contact = contact, actions = actions, validate = validate)
        ContactTypeDM(contact = contact)
        PhoneNumberTF(contact = contact, actions = actions, validate = validate)
        EmailTF(contact = contact, actions = actions, validate = validate)
        Button(
            onClick = onSubmit,
        ) {
            Text(text = "Save Contact")
        }
    }
}

@Composable
fun PhoneNumberTF(
    contact: Contact,
    actions: NewContactActions,
    validate: Boolean = false
) {
    NewContactTextField(
        value = contact.phone_number,
        label = "* Phone Number",
        icon = Icons.Default.Call,
        onValueChange = {
            actions.onContactChanged(contact)
            contact.phone_number = it
        },
        error = if (contact.isPhoneNumberValid().or(validate.not())) ""
        else "Phone Number is not valid."
    )
}

@Composable
fun NameTF(
    contact: Contact,
    actions: NewContactActions,
    validate: Boolean = false
) {
    NewContactTextField(
        value = contact.name,
        label = "* Name",
        icon = Icons.Default.Person,
        onValueChange = {
            actions.onContactChanged(contact)
            contact.name = it
        },
        error = if (contact.isNameValid().or(validate.not())) ""
        else "Name can not be empty."
    )
}

@Composable
fun SurnameTF(
    contact: Contact,
    actions: NewContactActions,
    validate: Boolean = false
) {
    NewContactTextField(
        value = contact.surname,
        label = "* Surname",
        icon = Icons.Default.Person,
        onValueChange = {
            actions.onContactChanged(contact)
            contact.surname = it
        },
        error = if (contact.isSurnameValid().or(validate.not())) ""
        else "Surname can not be empty."
    )
}

@Composable
fun EmailTF(
    contact: Contact,
    actions: NewContactActions,
    validate: Boolean = false
) {
    NewContactTextField(
        value = contact.email,
        label = "Email",
        icon = Icons.Default.Email,
        onValueChange = {
            actions.onContactChanged(contact)
            contact.email = it
        },
        error = if (contact.isEmailValid().or(validate.not())) ""
        else "Email is not valid."
    )
}

@Composable
fun ContactTypeDM(contact: Contact) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(ContactType.PERSONAL.toString()) }
    val options = ContactType.values().toList()
        .map { contactType -> contactType.toString() }.toList()

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = true }
            .semantics {
                contentDescription = "Contact type"
                role = Role.Button
            }
    ) {
        Text(selectedOption, Modifier.padding(16.dp))

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    selectedOption = option
                    contact.type = ContactType.values()
                        .first { contactType -> contactType.toString() == selectedOption }
                    expanded = false
                }, text = { Text(text = option) })
            }
        }
    }
}
