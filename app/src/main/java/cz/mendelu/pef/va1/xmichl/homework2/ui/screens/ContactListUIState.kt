package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

import cz.mendelu.pef.va1.xmichl.homework2.model.Contact

sealed class ContactListUIState {
    object Default : ContactListUIState()
    class Success(val contacts: List<Contact>) : ContactListUIState()

}