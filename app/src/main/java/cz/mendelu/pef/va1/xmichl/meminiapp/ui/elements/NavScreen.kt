package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import cz.mendelu.pef.va1.xmichl.meminiapp.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavScreen(
    appBarTitle: String,
    onBackClick: () -> Unit,
    fullScreenContent: Boolean = false,
    //actions: @Composable() (RowScope.() -> Unit),
    backArrowNeeded: Boolean = false,
    route: String,
    content: @Composable (paddingValues: PaddingValues) -> Unit,

    ) {
    val navItems = listOf(
        NavItem.TimeLine,
        NavItem.Today,
        NavItem.Map,
        NavItem.Search,
        NavItem.Settings
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = appBarTitle,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    if (backArrowNeeded) {
                        IconButton(onClick = { onBackClick() }) {
                            Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                        }
                    }
                },
                actions = {
                    Avatar()
                }
                //actions = actions,
            )
        },
        bottomBar = {
            NavigationBar {
                navItems.forEach { navItem ->
                    BottomNavigationItem(
                        icon = { Icon(imageVector = navItem.icon, contentDescription = "hello") },
                        label = {
                            if (route == navItem.route) {
                                Text(
                                    text = stringResource(navItem.title),
                                )
                            }
                        },
                        selected = route == navItem.route,
                        onClick = {}
                    )
                }
            }
        }
    ) {
        if (!fullScreenContent) {
            LazyColumn(modifier = Modifier.padding(it)) {
                item {
                    content(it)
                }
            }
        } else {
            content(it)
        }
    }

}

enum class NavItem(val route: String, @StringRes val title: Int, val icon: ImageVector) {
    TimeLine("timeline", R.string.timelineNavLabel, Icons.Default.CalendarViewDay),
    Today("today", R.string.todayNavLabel, Icons.Default.Today),
    Map("map", R.string.mapNavLabel, Icons.Default.Map),
    Search("search", R.string.searchNavLabel, Icons.Default.Search),
    Settings("settings", R.string.settingsNavLabel, Icons.Default.Settings),
}
