package itsme.ronjie.portfolio

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
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
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import itsme.ronjie.portfolio.presentation.screens.ContactScreen
import itsme.ronjie.portfolio.presentation.screens.HomeScreen
import itsme.ronjie.portfolio.presentation.screens.ProjectsScreen
import itsme.ronjie.portfolio.presentation.screens.SkillsScreen
import itsme.ronjie.portfolio.presentation.theme.ExtendedColors
import itsme.ronjie.portfolio.presentation.theme.LocalExtendedColors
import itsme.ronjie.portfolio.presentation.theme.androidGreen
import itsme.ronjie.portfolio.presentation.theme.cardBackground
import itsme.ronjie.portfolio.presentation.theme.darkBackground
import itsme.ronjie.portfolio.presentation.theme.iOSBlue
import itsme.ronjie.portfolio.presentation.utils.NoRippleInteractionSource
import org.jetbrains.compose.ui.tooling.preview.Preview

private data class NavigationItem(
    val title: String,
    val icon: ImageVector,
    val index: Int
)

@OptIn(ExperimentalMaterial3Api::class, ExperimentalHazeMaterialsApi::class)
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

    MaterialTheme(
        colorScheme = darkColorScheme(
            primary = iOSBlue,
            secondary = androidGreen,
            background = darkBackground,
            surface = cardBackground
        )
    ) {
        CompositionLocalProvider(LocalExtendedColors provides extendedColors) {
            var selectedTab by remember { mutableStateOf(0) }
            val hazeState = remember { HazeState() }

            val items = listOf(
                NavigationItem("Home", Icons.Default.Home, 0),
                NavigationItem("Skills", Icons.Default.Build, 1),
                NavigationItem("Projects", Icons.Default.Work, 2),
                NavigationItem("Contact", Icons.Default.AccountCircle, 3)
            )

            Scaffold(
                containerColor = Color.Transparent,
                contentColor = MaterialTheme.colorScheme.onBackground,
                contentWindowInsets = WindowInsets(0)
            ) { padding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .background(gradientBackground)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .hazeSource(state = hazeState)
                    ) {
                        AnimatedContent(
                            targetState = selectedTab,
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
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
                                0 -> HomeScreen()
                                1 -> SkillsScreen()
                                2 -> ProjectsScreen()
                                3 -> ContactScreen()
                            }
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp)
                        ) {
                            TabRow(
                                selectedTabIndex = selectedTab,
                                containerColor = Color.Transparent,
                                contentColor = Color.White,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .hazeEffect(
                                        state = hazeState,
                                        style = HazeMaterials.thin(
                                            containerColor = Color.Red.copy(alpha = 0.9f)
                                        )
                                    ),
                                divider = {}
                            ) {
                                items.forEach { item ->
                                    Tab(
                                        selected = selectedTab == item.index,
                                        onClick = { selectedTab = item.index },
                                        text = { Text(item.title) },
                                        icon = { Icon(item.icon, contentDescription = item.title) },
                                        selectedContentColor = Color.White,
                                        unselectedContentColor = Color.White.copy(alpha = 0.65f),
                                        interactionSource = NoRippleInteractionSource
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
