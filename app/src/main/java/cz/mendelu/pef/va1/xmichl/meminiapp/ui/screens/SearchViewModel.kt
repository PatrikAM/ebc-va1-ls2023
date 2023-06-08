package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import cz.mendelu.pef.va1.xmichl.meminiapp.architecture.BaseViewModel
import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory
import cz.mendelu.pef.va1.xmichl.meminiapp.utils.DateUtils
import java.util.Date

class SearchViewModel: SearchActions, BaseViewModel() {
    val searchUIState: MutableState<SearchUIState> =
        mutableStateOf(SearchUIState.Default)

    var data: SearchData = SearchData()

    override fun onTitleChanged(title: String?) {
        data.title = title
        searchUIState.value = SearchUIState.TitleChanged
    }

    override fun onMonthChanged(date: Date?) {
        data.date = date
        searchUIState.value = SearchUIState.MonthSelected
    }

    override fun onSearchClick() {
        data.titleFitlter = data.title
        data.dateFilter = data.date
        if (data.date != null) {
            if (data.title != null) {
                data.filterFunction = { memory ->
                    isMatch(memory.title, data.titleFitlter!!) &&
                            DateUtils.isSameMonthAndYearAsSystemTime(date = data.dateFilter!!, date2 = memory.date)
                }
            } else {
                data.filterFunction = {memory ->
                    DateUtils.isSameMonthAndYearAsSystemTime(date = data.dateFilter!!, date2 = memory.date)
                }
            }
        } else if (data.title != null) {
            data.filterFunction = {memory ->
                isMatch(memory.title, data.titleFitlter!!)
            }
        } else {
            data.filterFunction = { true }
        }
        searchUIState.value = SearchUIState.MemoriesSearched
    }

    private fun isMatch(memory: String, data: String): Boolean {
        val titleRegex = Regex(".*$data.*", RegexOption.IGNORE_CASE)
        return memory.matches(titleRegex)
    }
}