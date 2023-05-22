package cz.pef.mendelu.exam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "counters")
data class Counter(
    @ColumnInfo(name = "name")
    var name: String,
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Long? = null

    @ColumnInfo(name = "current_value")
    var currentValue: Int? = null

    @ColumnInfo(name = "minimum_value")
    var minimumValue: Int? = null

    @ColumnInfo(name = "maximum_value")
    var maximumValue: Int? = null



}
