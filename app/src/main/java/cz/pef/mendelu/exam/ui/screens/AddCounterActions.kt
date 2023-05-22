package cz.pef.mendelu.exam.ui.screens

interface AddCounterActions {
    fun saveCounter()
    fun deleteCounter()
    fun onNameChanged(text: String)
    fun onCurrentValueChanged(currentValue: String)
    fun onMinimumValueChanged(minimumValue: String)
    fun onMaximumValueChanged(maximumValue: String)
}