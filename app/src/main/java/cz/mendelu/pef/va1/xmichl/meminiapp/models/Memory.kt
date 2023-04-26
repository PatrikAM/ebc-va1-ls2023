package cz.mendelu.pef.va1.xmichl.meminiapp.models

import android.graphics.Bitmap
import android.location.Location
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memories")
data class Memory(
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "primary_photo")
    var primaryPhoto: Bitmap,

    @ColumnInfo(name = "primary_photo")
    var photo1: Bitmap?,

    @ColumnInfo(name = "primary_photo")
    var photo2: Bitmap?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "date")
    var date: Long,

    @Embedded
    @ColumnInfo(name = "location")
    var location: Location?,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Long? = null
}