package cz.mendelu.pef.va1.xmichl.homework2.ui.elements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.va1.xmichl.homework2.model.Contact
import cz.mendelu.pef.va1.xmichl.homework2.model.ContactType
import cz.mendelu.pef.va1.xmichl.homework2.ui.screens.NewContactActions
import cz.mendelu.pef.va1.xmichl.homework2.ui.screens.NewContactScreenData


@Composable
fun ContactForm(
    onSubmit: () -> Unit,
    data: NewContactScreenData,
    actions: NewContactActions,
    validate: Boolean = false
) {
    var selectedType by remember { mutableStateOf("") }

    val options = listOf(
        ContactType.PERSONAL.toString(),
        ContactType.WORK.toString()
    )

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        NameTF(contact = data.contact, actions = actions, validate = validate)
        SurnameTF(contact = data.contact, actions = actions, validate = validate)

        ContactTypeDMOutlined(contact = data.contact)
        PhoneNumberTF(contact = data.contact, actions = actions, validate = validate)
        EmailTF(contact = data.contact, actions = actions, validate = validate)
        Button(
            onClick = onSubmit,
            modifier = Modifier
                .align(CenterHorizontally)
                .padding(20.dp)
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
            if (it.matches(Regex("[0-9]{0,15}"))) {
                actions.onContactChanged(contact)
                contact.phone_number = it
            }
        },
        error = if (contact.isPhoneNumberValid().or(validate.not())) ""
        else "Phone number consists of at least 7 digits."
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactTypeDMOutlined(contact: Contact) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(ContactType.PERSONAL.toString()) }
    val options = ContactType.values().toList()
        .map { contactType -> contactType.toString() }.toList()
    Column {
        Box(
            modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth(fraction = 0.9f)
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = selectedOption,
                    label = { Text(text = "Contact Type") },
                    onValueChange = { },
                    //label = { Text("Work/Personal") },
                    leadingIcon = {
                        if (selectedOption == ContactType.PERSONAL.toString())
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Personal"
                            )
                        else
                            Icon(
                                imageVector = Icons.Default.Build,
                                contentDescription = "Personal"
                            )
                    },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = expanded
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    modifier = Modifier
                        .exposedDropdownSize(matchTextFieldWidth = true)
                        .fillMaxWidth(),
                    onDismissRequest = {
                        expanded = false
                    }
                ) {
                    options.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option) },
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                selectedOption = option
                                contact.type = ContactType.values()
                                    .first { contactType -> contactType.toString() == selectedOption }
                                expanded = false
                            })
                    }
                }
            }
        }
    }
}
