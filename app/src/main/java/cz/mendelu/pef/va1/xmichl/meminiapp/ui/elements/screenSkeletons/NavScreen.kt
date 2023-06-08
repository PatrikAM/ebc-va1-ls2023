package cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.screenSkeletons

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarViewDay
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Today
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import cz.mendelu.pef.va1.xmichl.meminiapp.R
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.Destination
import cz.mendelu.pef.va1.xmichl.meminiapp.navigation.INavigationRouter
import cz.mendelu.pef.va1.xmichl.meminiapp.ui.elements.Avatar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavScreen(
    appBarTitle: String,
    onBackClick: () -> Unit = {},
    boxContent: Boolean = false,
    contentModifier: Modifier = Modifier,
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
        NavItem.Map,
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
                    IconButton(onClick = { navigation.navigateToSettingsScreen() }) {
                        Icon(imageVector = Icons.Filled.Settings, contentDescription = "")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                navItems.forEach { navItem ->
                    NavigationBarItem(
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
        if (!boxContent) {
            LazyColumn(
                modifier = contentModifier.padding(it).fillMaxSize(),
                horizontalAlignment = CenterHorizontally,
            ) {
                item {
                        content(it)
                }
            }
        } else {
            Box(
                modifier = contentModifier
                    .padding(it)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                content(it)
            }
        }
    }

}

enum class NavItem(val destination: Destination, @StringRes val title: Int, val icon: ImageVector) {
    TimeLine(Destination.TimeLineScreen, R.string.timelineNavLabel, Icons.Default.CalendarViewDay),
    Today(Destination.TodayScreen, R.string.todayNavLabel, Icons.Default.Today),
    Map(Destination.MapScreen, R.string.mapNavLabel, Icons.Default.Map),
    Search(Destination.SearchScreen, R.string.searchNavLabel, Icons.Default.Search),
    //Settings("settings", R.string.settingsNavLabel, Icons.Default.Settings),
}
