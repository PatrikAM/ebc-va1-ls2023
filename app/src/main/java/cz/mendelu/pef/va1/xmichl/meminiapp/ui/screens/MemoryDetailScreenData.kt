package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory

class MemoryDetailScreenData {
    var memory: Memory = Memory(
        title = "",
        date = System.currentTimeMillis(),
        primaryPhoto = "",
        photo1 = null,
        photo2 = null,
        description = null,
        longitude = null,
        latitude = null
    )

    var loading: Boolean = true
    var confirmDialogPresented: Boolean = false
}