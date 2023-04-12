package cz.mendelu.pef.va1.xmichl.homework2.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.va1.xmichl.homework2.architecture.BaseViewModel
import cz.mendelu.pef.va1.xmichl.homework2.database.IContactsRepository
import cz.mendelu.pef.va1.xmichl.homework2.model.Contact
import kotlinx.coroutines.launch

class NewContactViewModel(
    private val repository: IContactsRepository
) : BaseViewModel(), NewContactActions {

    val newContactUIState: MutableState<NewContactUIState> =
        mutableStateOf(NewContactUIState.Default)
    var data: NewContactScreenData = NewContactScreenData()

    override fun saveContact() {
        launch {
            val id = repository.insert(data.contact)
            if (id > 0) {
                newContactUIState.value = NewContactUIState.ContactSaved
            } else {
                // error
            }
        }
    }

    override fun onContactChanged(contact: Contact) {
        data.contact = contact
        newContactUIState.value = NewContactUIState.ContactChanged
    }


}