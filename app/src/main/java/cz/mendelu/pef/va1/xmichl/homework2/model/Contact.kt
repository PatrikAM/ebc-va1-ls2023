package cz.mendelu.pef.va1.xmichl.homework2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "surname")
    val surname: String,

    @ColumnInfo(name = "phone_number")
    val phone_number: String,

    @ColumnInfo(name = "type")
    val type: ContactType,

    @ColumnInfo(name = "email")
    val email: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Long? = null

}

enum class ContactType {
    PERSONAL, WORK
}
