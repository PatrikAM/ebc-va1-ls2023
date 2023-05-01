package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

interface AddEditMemoryActions {
    fun saveMemory()
    fun onTitleChanged(title: String)
    fun onDescriptionChanged(desc: String)
    fun onDateChanged(date: Long?)
//    fun onPlaceChanged()
//    fun onPhotoChanged(index: Int, photo: Bitmap)
}
