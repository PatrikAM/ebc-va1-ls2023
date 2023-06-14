package cz.mendelu.pef.va1.xmichl.meminiapp.ui.activities.states

sealed class SplashScreenUiState {
    object Default : SplashScreenUiState()
//    object RunForAFirstTime : SplashScreenUiState()
    object ContinueToApp : SplashScreenUiState()
}