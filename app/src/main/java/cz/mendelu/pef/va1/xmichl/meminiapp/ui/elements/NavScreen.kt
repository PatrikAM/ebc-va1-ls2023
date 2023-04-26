package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.RowScope
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
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavScreen(
    appBarTitle: String,
    onBackClick: () -> Unit = {},
    fullScreenContent: Boolean = false,
    //actions: @Composable() (RowScope.() -> Unit),
    backArrowNeeded: Boolean = false,
    destination: Destination,
    navigation: INavigationRouter,
    floatingActionButton: @Composable() (() -> Unit) = {},
    content: @Composable (paddingValues: PaddingValues) -> Unit,

    ) {
    val navItems = listOf(
        NavItem.TimeLine,
        NavItem.Today,
        //NavItem.Map,
        NavItem.Search,
        //NavItem.Settings
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
                    //actions()
                    Avatar()
                }
            )
        },
        bottomBar = {
            NavigationBar {
                navItems.forEach { navItem ->
                    NavigationBarItem (
                        icon = { Icon(imageVector = navItem.icon, contentDescription = "hello") },
                        label = {
                            if (destination == navItem.destination) {
                                Text(
                                    text = stringResource(navItem.title),
                                )
                            }
                        },
                        selected = destination == navItem.destination,
                        onClick = {
                            if (destination != navItem.destination) {
                                navigation.getNavController().navigate(navItem.destination.route)
                            }
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        },
        floatingActionButton = floatingActionButton
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

enum class NavItem(val destination: Destination, @StringRes val title: Int, val icon: ImageVector) {
    TimeLine(Destination.TimeLineScreen, R.string.timelineNavLabel, Icons.Default.CalendarViewDay),
    Today(Destination.TodayScreen, R.string.todayNavLabel, Icons.Default.Today),
    //Map("map", R.string.mapNavLabel, Icons.Default.Map),
    Search(Destination.SearchScreen, R.string.searchNavLabel, Icons.Default.Search),
    //Settings("settings", R.string.settingsNavLabel, Icons.Default.Settings),
}
