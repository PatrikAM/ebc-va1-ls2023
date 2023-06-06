package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.content.Context
import android.net.Uri

interface AddEditMemoryActions {
    fun saveMemory(context: Context)
    fun onTitleChanged(title: String)
    fun onDescriptionChanged(desc: String?)
    fun onDateChanged(date: Long)
    fun onPhotoPickerExit(uri: Uri, context: Context)
    fun onPhotoCleared(context: Context)
    fun onPhotoPickerStart(index: Int)
    fun onLocationChanged(latitude: Double?, longitude: Double?)
}
