package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

import cz.mendelu.pef.va1.xmichl.homework2.model.Contact
import cz.mendelu.pef.va1.xmichl.homework2.model.ContactType

class NewContactScreenData {
    var contact: Contact = Contact(
        name = "",
        surname = "",
        phone_number = "",
        type = ContactType.PERSONAL,
        email = ""
    )
}