package cz.pef.mendelu.exam.ui.screens

import cz.pef.mendelu.exam.model.Counter

class AddCounterScreenData {
    var counter: Counter = Counter("")
    var loading: Boolean = true
    var counterNameError: Int? = null
    var counterCurrentValueError: Int? = null
    var counterMaximumValueError: Int? = null
    var counterMinimumValueError: Int? = null
}