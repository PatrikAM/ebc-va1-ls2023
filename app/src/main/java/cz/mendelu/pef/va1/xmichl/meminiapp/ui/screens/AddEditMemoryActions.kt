package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.net.Uri

interface AddEditMemoryActions {
    fun saveMemory()
    fun onTitleChanged(title: String)
    fun onDescriptionChanged(desc: String?)
    fun onDateChanged(date: Long)
    fun onPhotoPickerExit(uri: Uri?)
    fun onPhotoPickerStart(index: Int)
    fun onLocationChanged(latitude: Double?, longitude: Double?)
}
