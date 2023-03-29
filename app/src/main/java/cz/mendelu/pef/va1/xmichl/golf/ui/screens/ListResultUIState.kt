package cz.mendelu.pef.va1.xmichl.golf.ui.screens

import cz.mendelu.pef.va1.xmichl.golf.model.Golfist

sealed class ListResultUIState {
    object Default : ListResultUIState()
    class Success(val golfists: List<Golfist>) : ListResultUIState()
}