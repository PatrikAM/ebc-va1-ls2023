package cz.mendelu.pef.va1.xmichl.golf.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "golfists")
data class Golfist(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "score")
    val score: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Long? = null

}
