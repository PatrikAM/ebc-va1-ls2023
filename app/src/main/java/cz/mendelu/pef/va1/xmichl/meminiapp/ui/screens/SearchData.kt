package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import cz.mendelu.pef.va1.xmichl.meminiapp.models.Memory
import java.util.Date

class SearchData {
    var title: String? = null
    var date: Date? = null

    var titleFitlter: String? = null
    var dateFilter: Date? = null

    var filterFunction: ((Memory) -> Boolean)? = null
}