package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import cz.mendelu.pef.va1.xmichl.meminiapp.architecture.BaseViewModel

class MapPickerPickerViewModel : BaseViewModel(), MapPickerActions {
    var latitude: Double? = null
    var longitude: Double? = null
    var locationChanged = false

    override fun onLocationChanged(latitude: Double, longitude: Double) {
        this.latitude = latitude
        this.longitude = longitude
        locationChanged = true
    }

}