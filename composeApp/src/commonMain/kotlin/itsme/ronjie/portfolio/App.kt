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
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialExpressiveTheme
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
import androidx.window.core.layout.WindowWidthSizeClass
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import itsme.ronjie.portfolio.presentation.navigation.AdaptiveNavigationBar
import itsme.ronjie.portfolio.presentation.navigation.AdaptiveNavigationRail
import itsme.ronjie.portfolio.presentation.navigation.NavigationItem
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
    items: List<NavigationItem>,
    hazeState: HazeState
) {
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val windowSizeClass = adaptiveInfo.windowSizeClass

    Box(Modifier.fillMaxSize()) {
        when (windowSizeClass.windowWidthSizeClass) {
            WindowWidthSizeClass.MEDIUM, WindowWidthSizeClass.EXPANDED -> {
                AdaptiveNavigationRail(
                    animatedVisibilityScope = animatedVisibilityScope,
                    items = items,
                    hazeState = hazeState
                )
            }

            else -> {
                AdaptiveNavigationBar(
                    animatedVisibilityScope = animatedVisibilityScope,
                    items = items,
                    hazeState = hazeState
                )
            }
        }
    }
}
