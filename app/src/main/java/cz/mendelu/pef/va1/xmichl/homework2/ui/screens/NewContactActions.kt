package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

import cz.mendelu.pef.va1.xmichl.homework2.model.Contact

interface NewContactActions {
    fun saveContact()
    fun onContactChanged(contact: Contact)
}