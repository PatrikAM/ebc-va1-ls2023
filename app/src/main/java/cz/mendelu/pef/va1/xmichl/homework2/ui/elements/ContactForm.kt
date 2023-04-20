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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import cz.mendelu.pef.va1.xmichl.homework2.R
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

    Column(
        horizontalAlignment = CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        NameTF(contact = data.contact, actions = actions, validate = validate)
        SurnameTF(contact = data.contact, actions = actions, validate = validate)

        ContactTypeDMOutlined(contact = data.contact, actions = actions)
        PhoneNumberTF(contact = data.contact, actions = actions, validate = validate)
        EmailTF(contact = data.contact, actions = actions, validate = validate)

//        Button(
//            onClick = onSubmit,
//            modifier = Modifier
//                .align(CenterHorizontally)
//                .padding(20.dp)
//        ) {
//            Text(text = "Save Contact")
//        }

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
        label = stringResource(R.string.phoneNumerLabel),
        icon = Icons.Default.Call,
        onValueChange = {
            actions.onPhoneNumberChanged(it)
//            if (it.matches(Regex("[0-9]{0,15}"))) {
//                actions.onContactChanged(contact)
//                contact.phone_number = it
//            }
        },
        error = if (contact.isPhoneNumberValid().or(validate.not()))
            ""
        else
            stringResource(R.string.phoneNumebrError)
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
        label = stringResource(R.string.nameLabel),
        icon = Icons.Default.Person,
        onValueChange = {
            actions.onNameChanged(it)
//            actions.onContactChanged(contact)
//            contact.name = it
        },
        error = if (contact.isNameValid().or(validate.not())) ""
        else stringResource(R.string.nameError)
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
        label = stringResource(R.string.surnameLabel),
        icon = Icons.Default.Person,
        onValueChange = {
            actions.onSurnameChanged(it)
//            actions.onContactChanged(contact)
//            contact.surname = it
        },
        error = if (contact.isSurnameValid().or(validate.not())) ""
        else stringResource(R.string.surnameError)
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
        label = stringResource(R.string.emailOptionalLabel),
        icon = Icons.Default.Email,
        onValueChange = {
            actions.onEmailChanged(it)
//            actions.onContactChanged(contact)
//            contact.email = it
        },
        error = if (contact.isEmailValid().or(validate.not())) ""
        else stringResource(R.string.emailError)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactTypeDMOutlined(
    contact: Contact,
    actions: NewContactActions,
) {
    var expanded by remember { mutableStateOf(false) }
    val options = ContactType.values().toList()
        .map { contactType -> contactType.toString() }.toList()
    Column {
        Box(
            modifier = Modifier
                .align(CenterHorizontally)
                .fillMaxWidth(fraction = 0.9f)
                .padding(top = 10.dp, bottom = 20.dp)
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {
                OutlinedTextField(
                    readOnly = true,
                    value = contact.type.toString(),
                    //label = { Text(text = "Contact Type") },
                    onValueChange = { },
                    label = { Text(stringResource(R.string.ContactTypeLabel)) },
                    leadingIcon = {
                        if (contact.type.toString() == ContactType.PERSONAL.toString())
                            Icon(
                                imageVector = Icons.Default.Home,
                                contentDescription = "Personal"
                            )
                        else
                            Icon(
                                imageVector = Icons.Default.Work,
                                contentDescription = "Work"
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
                            modifier = Modifier.fillMaxWidth(0.9f),
                            onClick = {
                                actions.onContactTypeChanged(option)
//                                selectedOption = option
//                                contact.type = ContactType.values()
//                                    .first { contactType ->
//                                        contactType.toString() == selectedOption
//                                    }
                                expanded = false
                            })
                    }
                }
            }
        }
    }
}
