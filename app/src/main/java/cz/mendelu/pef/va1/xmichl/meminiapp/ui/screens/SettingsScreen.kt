package cz.mendelu.pef.va1.xmichl.meminiapp.ui.screens

import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.PackageInfoCompat
import coil.compose.AsyncImage
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.database.MemoriesDatabase
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.ColorPicker
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons.BackArrowScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(
    navigation: INavigationRouter,
    viewModel: SettingsViewModel = getViewModel()
) {
    BackArrowScreen(
        appBarTitle = stringResource(R.string.settings_about),
        onBackClick = { navigation.returnBack() }
    ) {

        var data: SettingsScreenData by remember {
            mutableStateOf(viewModel.data)
        }

        viewModel.settingsUIState.value.let {
            when (it) {
                SettingsUIState.Default -> {}

                SettingsUIState.SettingsChanged -> {
                    data = viewModel.data
                    viewModel.settingsUIState.value = SettingsUIState.Default
                }

                is SettingsUIState.Success -> {
                    LaunchedEffect(it) {
                        data.selectedColor = it.color
                        data.color = it.color
                        data.loading = false
                        viewModel.settingsUIState.value = SettingsUIState.Default
                    }
                }

                SettingsUIState.Loading -> {
                    viewModel.initColor(LocalContext.current)
                }
            }
        }

        val context: Context = LocalContext.current

        val ver: Int = MemoriesDatabase
            .getDatabase(context = context)
            .openHelper
            .readableDatabase
            .version

        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                AsyncImage(model = R.drawable.memini_logo, contentDescription = "")

                Row {
                    Column(modifier = Modifier.padding(horizontal = 10.dp)) {
                        Text("DB version:")
                        getAppVersion(context)?.let {_ -> Text("APP version:")}
                        Text("Developer:")
                        Text("")
                        Text(stringResource(R.string.button_color))
                    }
                    Column {
                        Text("$ver")
                        getAppVersion(context)?.let { it1 -> Text(it1.versionName) }
                        Text(text = "Patrik Michl")
                        Text(text = "patrik.michl@mendelu.cz")
                        ColorPicker(
                            onColorSelected = {
                                viewModel.onColorChanged(it)
                            },
                            onColorConfirmed = {
                                viewModel.onColorSaved(
                                    context = context
                                )
                            },
                            color = data.color
                        )
                    }

                }
            }
        }

    }
}

data class AppVersion(
    val versionName: String,
    val versionNumber: Long,
)

fun getAppVersion(
    context: Context,
): AppVersion? {
    return try {
        val packageManager = context.packageManager
        val packageName = context.packageName
        val packageInfo = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            packageManager.getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(0))
        } else {
            packageManager.getPackageInfo(packageName, 0)
        }
        AppVersion(
            versionName = packageInfo.versionName,
            versionNumber = PackageInfoCompat.getLongVersionCode(packageInfo),
        )
    } catch (e: Exception) {
        null
    }
}