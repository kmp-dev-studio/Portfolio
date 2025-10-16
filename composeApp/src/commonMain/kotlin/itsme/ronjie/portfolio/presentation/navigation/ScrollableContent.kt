@file:OptIn(ExperimentalSharedTransitionApi::class)

package itsme.ronjie.portfolio.presentation.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource
import itsme.ronjie.portfolio.presentation.composables.ThemeToggleButton
import itsme.ronjie.portfolio.presentation.screens.ContactScreen
import itsme.ronjie.portfolio.presentation.screens.HomeScreen
import itsme.ronjie.portfolio.presentation.screens.ProjectsScreen
import itsme.ronjie.portfolio.presentation.screens.SkillsScreen

@Composable
fun SharedTransitionScope.ScrollableContent(
    animatedVisibilityScope: AnimatedVisibilityScope,
    scrollState: ScrollState,
    sectionPositions: MutableMap<Int, Float>,
    hazeState: HazeState,
    showThemeToggle: Boolean = false
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .hazeSource(state = hazeState)
    ) {
        if (showThemeToggle) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.End
            ) { ThemeToggleButton() }
        }

        Box(
            modifier = Modifier.onGloballyPositioned { coordinates ->
                sectionPositions[0] = coordinates.positionInParent().y
            }
        ) { HomeScreen(animatedVisibilityScope) }

        Box(
            modifier = Modifier.onGloballyPositioned { coordinates ->
                sectionPositions[1] = coordinates.positionInParent().y
            }
        ) { SkillsScreen() }

        Box(
            modifier = Modifier.onGloballyPositioned { coordinates ->
                sectionPositions[2] = coordinates.positionInParent().y
            }
        ) { ProjectsScreen() }

        Box(
            modifier = Modifier.onGloballyPositioned { coordinates ->
                sectionPositions[3] = coordinates.positionInParent().y
            }
        ) { ContactScreen() }
    }
}
