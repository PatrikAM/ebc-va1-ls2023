package cz.mendelu.pef.va1.xmichl.meminiapp.models

import android.location.Address
import android.location.Geocoder
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import cz.mendelu.pef.va1.xmichl.meminiapp.MeminiApp
import java.util.*

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "latitude")
    var latitude: Double?,
    @Json(name = "longitude")
    var longitude: Double?
) {
    fun getNearestCity(): String? {
        if (latitude == null || longitude == null) return null
        val gcd = Geocoder(MeminiApp.appContext, Locale.getDefault())
        val addresses: List<Address>? = gcd
            .getFromLocation(
                this.latitude!!,
                this.longitude!!,
                1
            )
        return if ((addresses != null) && addresses.isNotEmpty()) {
            addresses[0].locality
        } else {
            null
        }
    }
}