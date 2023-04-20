package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.va1.xmichl.homework2.architecture.BaseViewModel
import cz.mendelu.pef.va1.xmichl.homework2.database.IContactsRepository
import cz.mendelu.pef.va1.xmichl.homework2.model.Contact
import cz.mendelu.pef.va1.xmichl.homework2.model.ContactType
import kotlinx.coroutines.launch

class NewContactViewModel(
    private val repository: IContactsRepository
) : BaseViewModel(), NewContactActions {

    val newContactUIState: MutableState<NewContactUIState> =
        mutableStateOf(NewContactUIState.Default)
    var data: NewContactScreenData = NewContactScreenData()

    override fun saveContact() {
        if (data.contact.isContactValid()) {
            launch {
                val id = repository.insert(data.contact)
                if (id > 0) {
                    newContactUIState.value = NewContactUIState.ContactSaved
                } else {
                    // error
                }
            }
        } else {
            newContactUIState.value = NewContactUIState.ContactWasNotSaved
        }
    }

    override fun onContactChanged(contact: Contact) {
        data.contact = contact
        newContactUIState.value = NewContactUIState.ContactChanged
    }

    override fun onPhoneNumberChanged(phoneNumber: String) {
        if (phoneNumber.matches(Regex("[0-9]{0,15}"))) {
            data.contact.phone_number = phoneNumber
            newContactUIState.value = NewContactUIState.ContactChanged
        }
    }

    override fun onNameChanged(name: String) {
        data.contact.name = name
        newContactUIState.value = NewContactUIState.ContactChanged
    }

    override fun onSurnameChanged(surname: String) {
        data.contact.surname = surname
        newContactUIState.value = NewContactUIState.ContactChanged
    }

    override fun onContactTypeChanged(type: String) {
        data.contact.type = ContactType.valueOf(type)
        newContactUIState.value = NewContactUIState.ContactChanged
    }

    override fun onEmailChanged(email: String) {
        data.contact.email = email
        newContactUIState.value = NewContactUIState.ContactChanged
    }

}