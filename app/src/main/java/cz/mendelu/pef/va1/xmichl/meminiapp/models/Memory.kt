package cz.mendelu.pef.va1.xmichl.meminiapp.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "memories")
data class Memory(
    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "primary_photo")
    var primaryPhoto: ByteArray?,

    @ColumnInfo(name = "second_photo")
    var photo1: ByteArray?,

    @ColumnInfo(name = "third_photo")
    var photo2: ByteArray?,

    @ColumnInfo(name = "description")
    var description: String?,

    @ColumnInfo(name = "date")
    var date: Long?,

    @ColumnInfo(name = "longitude")
    var longitude: Double?,

    @ColumnInfo(name = "latitude")
    var latitude: Double?,
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Long? = null
}