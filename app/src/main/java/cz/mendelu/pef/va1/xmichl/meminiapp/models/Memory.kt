package cz.mendelu.pef.va1.xmichl.meminiapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memories")
data class Memory(
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "primary_photo")
    var primaryPhoto: String,

    @ColumnInfo(name = "second_photo")
    var photo1: String?,

    @ColumnInfo(name = "third_photo")
    var photo2: String?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "date")
    var date: Long,

    @ColumnInfo(name = "longitude")
    var longitude: Double?,

    @ColumnInfo(name = "latitude")
    var latitude: Double?,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Long? = null

    fun hasLocation(): Boolean =
        latitude != null && longitude != null

    fun getImages(): MutableList<String> {
        val l: MutableList<String> = mutableListOf(primaryPhoto)

        if (photo1 != null) {
            l.add(photo1!!)
        }

        if (photo2 != null) {
            l.add(photo2!!)
        }

        return l
    }

}