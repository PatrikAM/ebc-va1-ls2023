package cz.mendelu.pef.va1.xmichl.meminiapp.utils

import java.text.SimpleDateFormat
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Locale

class DateUtils {
    companion object {
        private val DATE_FORMAT_CS = "dd. MM. yyyy"
        private val DATE_FORMAT_EN = "yyyy/MM/dd"

        fun getDateString(unixTime: Long): String{
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = unixTime
            val format: SimpleDateFormat
            if (LanguageUtils.isLanguageCzech()){
                format = SimpleDateFormat(DATE_FORMAT_CS, Locale.GERMAN)
            } else {
                format = SimpleDateFormat(DATE_FORMAT_EN, Locale.ENGLISH)
            }
            return format.format(calendar.getTime())
        }

        fun getUnixTime(year: Int, month: Int, day: Int): Long {
            val calendar = Calendar.getInstance()
            calendar.set(year, month, day)
            return calendar.timeInMillis
        }

        fun getDateString(date: Date): String {
            val instant = date.toInstant()
            val zoneId = ZoneId.systemDefault()
            val zonedDateTime = instant.atZone(zoneId)
            val localDate = zonedDateTime.toLocalDate()
            val formatter = DateTimeFormatter.ofPattern("MMM yyyy", Locale.getDefault())
            return localDate.format(formatter)
        }

        fun isSameMonthAndYearAsSystemTime(date: Date, date2: Long): Boolean {
            val currentDate = Date(date2)
            val calendar = Calendar.getInstance()
            calendar.time = date
            val givenMonth = calendar.get(Calendar.MONTH)
            val givenYear = calendar.get(Calendar.YEAR)

            calendar.time = currentDate
            val currentMonth = calendar.get(Calendar.MONTH)
            val currentYear = calendar.get(Calendar.YEAR)

            return givenMonth == currentMonth && givenYear == currentYear
        }

    }
}