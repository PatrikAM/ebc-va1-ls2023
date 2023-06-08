package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import java.util.Date

interface SearchActions {
    fun onTitleChanged(title: String?)
    fun onMonthChanged(date: Date?)
    fun onSearchClick()
}