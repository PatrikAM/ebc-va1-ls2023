package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory

class AddEditMemoryData {
}

class AddEditScreenData {
    var memory: Memory = Memory(
        title = "",
        date = null,
        primaryPhoto = null,
        photo1 = null,
        photo2 = null,
        description = null,
        longitude = null,
        latitude = null
    )
    var loading: Boolean = true
}
