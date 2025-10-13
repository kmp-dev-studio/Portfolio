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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import itsme.ronjie.portfolio.presentation.composables.BottomNavigationBar
import itsme.ronjie.portfolio.presentation.composables.StatusBar
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
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val extendedColors = ExtendedColors()

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

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                extendedColors.gradientStart,
                                extendedColors.gradientMiddle,
                                extendedColors.gradientEnd
                            )
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    StatusBar()

                    Spacer(Modifier.height(16.dp))

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                    ) {
                        AnimatedContent(
                            targetState = selectedTab,
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
                    }

                    Spacer(Modifier.height(16.dp))

                    BottomNavigationBar(
                        selectedTab = selectedTab,
                        onTabSelected = { selectedTab = it }
                    )
                }
            }
        }
    }
}
