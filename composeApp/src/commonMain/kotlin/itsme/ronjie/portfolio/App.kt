@file:OptIn(ExperimentalHazeMaterialsApi::class, ExperimentalSharedTransitionApi::class)

package itsme.ronjie.portfolio

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import itsme.ronjie.portfolio.presentation.screens.ContactScreen
import itsme.ronjie.portfolio.presentation.screens.HomeScreen
import itsme.ronjie.portfolio.presentation.screens.ProjectsScreen
import itsme.ronjie.portfolio.presentation.screens.SkillsScreen
import itsme.ronjie.portfolio.presentation.screens.SplashScreen
import itsme.ronjie.portfolio.presentation.theme.ExtendedColors
import itsme.ronjie.portfolio.presentation.theme.LocalExtendedColors
import itsme.ronjie.portfolio.presentation.theme.androidGreen
import itsme.ronjie.portfolio.presentation.theme.cardBackground
import itsme.ronjie.portfolio.presentation.theme.darkBackground
import itsme.ronjie.portfolio.presentation.theme.iOSBlue
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
    val extendedColors = ExtendedColors()

    val gradientBackground = Brush.verticalGradient(
        colors = listOf(
            extendedColors.gradientStart,
            extendedColors.gradientMiddle,
            extendedColors.gradientEnd
        )
    )

    MaterialExpressiveTheme(
        colorScheme = darkColorScheme(
            primary = iOSBlue,
            secondary = androidGreen,
            background = darkBackground,
            surface = cardBackground
        )
    ) {
        CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
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
                                hazeState = hazeState,
                                gradientBackground = gradientBackground
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
    hazeState: HazeState,
    gradientBackground: Brush
) {
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val windowSizeClass = adaptiveInfo.windowSizeClass

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
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
                containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
            ) {
                items.forEach { item ->
                    NavigationBarItem(
                        selected = selectedTab == item.index,
                        onClick = { onTabSelected(item.index) },
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) }
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

@Composable
private fun SharedTransitionScope.AdaptiveNavigationRail(
    animatedVisibilityScope: AnimatedVisibilityScope,
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    items: List<NavigationItem>,
    hazeState: HazeState
) {
    Row(modifier = Modifier.fillMaxSize()) {
        NavigationRail(
            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.9f)
        ) {
            items.forEach { item ->
                NavigationRailItem(
                    selected = selectedTab == item.index,
                    onClick = { onTabSelected(item.index) },
                    icon = { Icon(item.icon, contentDescription = item.title) },
                    label = { Text(item.title) }
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
