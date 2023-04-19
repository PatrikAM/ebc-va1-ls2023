package cz.mendelu.pef.va1.xmichl.homework2.ui.elements

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cz.mendelu.pef.va1.xmichl.homework2.model.Contact

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactRow(contact: Contact) {
    ListItem(
        headlineText = { Text(text = "${contact.surname}, ${contact.name}") },
        leadingContent = { LetterAvatar(contact.name[0]) },
        overlineText = { },
        supportingText = { Text(text = contact.phone_number) },
    )
}