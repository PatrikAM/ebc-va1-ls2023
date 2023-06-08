package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory

class AddEditScreenData {
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

    var titleError: Int? = null
    var photoError: Int? = null

    var loading: Boolean = true
    var photoIndex: Int = 0

    var primaryPhotoPicked: String? = null
    var secondaryPhotoPicked: String? = null
    var ternaryPhotoPicked: String? = null
}
