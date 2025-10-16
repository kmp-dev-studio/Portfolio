@file:OptIn(ExperimentalHazeMaterialsApi::class, ExperimentalSharedTransitionApi::class)

package itsme.ronjie.portfolio

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialExpressiveTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import itsme.ronjie.portfolio.presentation.composables.ThemeToggleButton
import itsme.ronjie.portfolio.presentation.screens.ContactScreen
import itsme.ronjie.portfolio.presentation.screens.HomeScreen
import itsme.ronjie.portfolio.presentation.screens.ProjectsScreen
import itsme.ronjie.portfolio.presentation.screens.SkillsScreen
import itsme.ronjie.portfolio.presentation.screens.SplashScreen
import itsme.ronjie.portfolio.presentation.theme.LocalExtendedColors
import itsme.ronjie.portfolio.presentation.theme.LocalThemeManager
import itsme.ronjie.portfolio.presentation.theme.ThemeMode
import itsme.ronjie.portfolio.presentation.theme.cardBackground
import itsme.ronjie.portfolio.presentation.theme.darkBackground
import itsme.ronjie.portfolio.presentation.theme.darkExtendedColors
import itsme.ronjie.portfolio.presentation.theme.lightExtendedColors
import itsme.ronjie.portfolio.presentation.theme.rememberThemeManager
import org.jetbrains.compose.ui.tooling.preview.Preview

private data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val index: Int
)

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalHazeMaterialsApi::class,
    ExperimentalMaterial3ExpressiveApi::class
)
@Composable
@Preview
fun App() {
    val themeManager = rememberThemeManager(ThemeMode.DARK)
    val isDarkMode = themeManager.currentTheme.value == ThemeMode.DARK
    val extendedColors = if (isDarkMode) darkExtendedColors() else lightExtendedColors()
    val animationSpec = tween<Color>(durationMillis = 500)

    val gradientStart by animateColorAsState(
        targetValue = extendedColors.gradientStart,
        animationSpec = animationSpec
    )
    val gradientMiddle by animateColorAsState(
        targetValue = extendedColors.gradientMiddle,
        animationSpec = animationSpec
    )
    val gradientEnd by animateColorAsState(
        targetValue = extendedColors.gradientEnd,
        animationSpec = animationSpec
    )

    val gradientBackground = Brush.linearGradient(
        colors = listOf(gradientStart, gradientMiddle, gradientEnd),
        start = Offset(0f, 0f),
        end = Offset(1f, 1f)
    )

    val backgroundColor by animateColorAsState(
        targetValue = if (isDarkMode) darkBackground else Color(0xFFF5F5F5),
        animationSpec = animationSpec
    )
    val surfaceColor by animateColorAsState(
        targetValue = if (isDarkMode) cardBackground else Color.White,
        animationSpec = animationSpec
    )

    val colorScheme = if (isDarkMode) {
        darkColorScheme(
            background = backgroundColor,
            surface = surfaceColor
        )
    } else {
        lightColorScheme(
            background = backgroundColor,
            surface = surfaceColor
        )
    }

    MaterialExpressiveTheme(
        colorScheme = colorScheme
    ) {
        CompositionLocalProvider(
            LocalExtendedColors provides extendedColors,
            LocalThemeManager provides themeManager
        ) {
            var showSplash by remember { mutableStateOf(true) }
            var selectedTab by remember { mutableStateOf(0) }
            val hazeState = remember { HazeState() }

            val items = listOf(
                NavigationItem("Home", Icons.Default.Home, 0),
                NavigationItem("Skills", Icons.Default.Build, 1),
                NavigationItem("Projects", Icons.Default.Work, 2),
                NavigationItem("Contact", Icons.Default.AccountCircle, 3)
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(gradientBackground)
            ) {
                SharedTransitionLayout {
                    AnimatedContent(
                        targetState = showSplash,
                        label = "splash_to_main_transition",
                        transitionSpec = {
                            fadeIn(animationSpec = tween(300)) togetherWith
                                    fadeOut(animationSpec = tween(300))
                        }
                    ) { isShowingSplash ->
                        if (isShowingSplash) {
                            SplashScreen(
                                animatedVisibilityScope = this@AnimatedContent,
                                onSplashComplete = { showSplash = false }
                            )
                        } else {
                            MainContent(
                                animatedVisibilityScope = this@AnimatedContent,
                                selectedTab = selectedTab,
                                onTabSelected = { selectedTab = it },
                                items = items,
                                hazeState = hazeState
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalHazeMaterialsApi::class)
@Composable
private fun SharedTransitionScope.MainContent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    items: List<NavigationItem>,
    hazeState: HazeState
) {
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val windowSizeClass = adaptiveInfo.windowSizeClass

    Box(modifier = Modifier.fillMaxSize()) {
        when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.MEDIUM, WindowWidthSizeClass.EXPANDED -> {
                AdaptiveNavigationRail(
                    animatedVisibilityScope = animatedVisibilityScope,
                    selectedTab = selectedTab,
                    onTabSelected = onTabSelected,
                    items = items,
                    hazeState = hazeState
                )
            }

            else -> {
                AdaptiveNavigationBar(
                    animatedVisibilityScope = animatedVisibilityScope,
                    selectedTab = selectedTab,
                    onTabSelected = onTabSelected,
                    items = items,
                    hazeState = hazeState
                )
            }
        }
    }
}

@Composable
private fun SharedTransitionScope.AdaptiveNavigationBar(
    animatedVisibilityScope: AnimatedVisibilityScope,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    items: List<NavigationItem>,
    hazeState: HazeState
) {
    Scaffold(
        containerColor = Color.Transparent,
        bottomBar = {
            NavigationBar(
                containerColor = Color.Transparent,
                modifier = Modifier
                    .hazeEffect(
                        state = hazeState,
                        style = HazeMaterials.thick(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                        )
                    )
            ) {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = selectedTab == item.index,
                        onClick = { onTabSelected(item.index) },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                fontWeight = if (selectedTab == item.index) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.onBackground,
                            selectedTextColor = MaterialTheme.colorScheme.onBackground,
                            unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(0.65f),
                            unselectedTextColor = MaterialTheme.colorScheme.onBackground.copy(0.65f),
                            indicatorColor = MaterialTheme.colorScheme.surface.copy(0.75f)
                        )
                    )
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .hazeSource(state = hazeState)
                .hazeEffect(
                    state = hazeState,
                    style = HazeMaterials.thin(
                        containerColor = Color.Red.copy(alpha = 0.9f)
                    )
                )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    ThemeToggleButton()
                }

                AnimatedContent(
                    targetState = selectedTab,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    transitionSpec = {
                        slideInHorizontally(
                            initialOffsetX = { if (targetState > initialState) it else -it }
                        ) + fadeIn() togetherWith
                                slideOutHorizontally(
                                    targetOffsetX = { if (targetState > initialState) -it else it }
                                ) + fadeOut()
                    }
                ) { tab ->
                    when (tab) {
                        0 -> HomeScreen(animatedVisibilityScope)
                        1 -> SkillsScreen()
                        2 -> ProjectsScreen()
                        3 -> ContactScreen()
                    }
                }
            }
        }
    }
}

@Composable
private fun SharedTransitionScope.AdaptiveNavigationRail(
    animatedVisibilityScope: AnimatedVisibilityScope,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    items: List<NavigationItem>,
    hazeState: HazeState
) {
    Row(Modifier.fillMaxSize()) {
        NavigationRail(
            containerColor = Color.Transparent,
            modifier = Modifier
                .hazeEffect(
                    state = hazeState,
                    style = HazeMaterials.thick(
                        containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                    )
                ),
            header = { ThemeToggleButton() }
        ) {
            items.forEach { item ->
                NavigationRailItem(
                    selected = selectedTab == item.index,
                    onClick = { onTabSelected(item.index) },
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = {
                        Text(
                            text = item.title,
                            fontWeight = if (selectedTab == item.index) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = NavigationRailItemDefaults.colors(
                        selectedIconColor = MaterialTheme.colorScheme.onBackground,
                        selectedTextColor = MaterialTheme.colorScheme.onBackground,
                        unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(0.65f),
                        unselectedTextColor = MaterialTheme.colorScheme.onBackground.copy(0.65f),
                        indicatorColor = MaterialTheme.colorScheme.surface.copy(0.5f)
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(state = hazeState)
                .hazeEffect(
                    state = hazeState,
                    style = HazeMaterials.thin(
                        containerColor = Color.Red.copy(alpha = 0.9f)
                    )
                )
        ) {
            AnimatedContent(
                targetState = selectedTab,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                transitionSpec = {
                    slideInHorizontally(
                        initialOffsetX = { if (targetState > initialState) it else -it }
                    ) + fadeIn() togetherWith
                            slideOutHorizontally(
                                targetOffsetX = { if (targetState > initialState) -it else it }
                            ) + fadeOut()
                }
            ) { tab ->
                when (tab) {
                    0 -> HomeScreen(animatedVisibilityScope)
                    1 -> SkillsScreen()
                    2 -> ProjectsScreen()
                    3 -> ContactScreen()
                }
            }
        }
    }
}
