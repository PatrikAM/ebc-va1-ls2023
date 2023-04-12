package cz.mendelu.pef.va1.xmichl.homework2.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "surname")
    var surname: String,

    @ColumnInfo(name = "phone_number")
    var phone_number: String,

    @ColumnInfo(name = "type")
    var type: ContactType,

    @ColumnInfo(name = "email")
    var email: String
) {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    var id: Long? = null

    fun isEmailValid(): Boolean {
        val emailRegex = Regex("^([\\w\\d.+\\-]+)@([\\w\\d\\-]+)\\.([\\w]{2,})(\\.[\\w]{2,})?$")
        return email.isEmpty().or(emailRegex.matches(email))
    }

    fun isNameValid(): Boolean = name.isNotEmpty()

    fun isSurnameValid(): Boolean = surname.isNotEmpty()

    fun isPhoneNumberValid(): Boolean {
        val phoneRegex = Regex("^\\+?[0-9]{6,}$")
        return phoneRegex.matches(phone_number)
    }

    fun isContactValid(): Boolean {
        return isEmailValid()
            .and(isNameValid())
            .and(isSurnameValid())
            .and(isPhoneNumberValid())
    }

}

enum class ContactType {
    PERSONAL,
    WORK
}
