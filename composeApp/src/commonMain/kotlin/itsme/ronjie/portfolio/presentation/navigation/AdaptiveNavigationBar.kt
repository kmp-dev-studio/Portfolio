@file:OptIn(ExperimentalSharedTransitionApi::class, ExperimentalHazeMaterialsApi::class)

package itsme.ronjie.portfolio.presentation.navigation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import itsme.ronjie.portfolio.presentation.composables.SocialLinks
import itsme.ronjie.portfolio.presentation.composables.ThemeToggleButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SharedTransitionScope.AdaptiveNavigationBar(
    animatedVisibilityScope: AnimatedVisibilityScope,
    items: List<NavigationItem>,
    hazeState: HazeState
) {
    val scrollState = rememberScrollState()
    var selectedSection by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    val sectionPositions = remember { mutableMapOf<Int, Float>() }

    Box(Modifier.fillMaxSize()) {
        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                TopAppBar(
                    title = {},
                    actions = {
                        SocialLinks()
                        ThemeToggleButton()
                    },
                    colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
                )
            },
            bottomBar = {
                NavigationBar(containerColor = Color.Transparent) {
                    items.forEach { item ->
                        NavigationBarItem(
                            selected = selectedSection == item.index,
                            onClick = {
                                coroutineScope.launch {
                                    val targetPosition = sectionPositions[item.index] ?: 0f
                                    scrollState.animateScrollTo(
                                        value = targetPosition.toInt(),
                                        animationSpec = tween(durationMillis = 500)
                                    )
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = item.icon,
                                    contentDescription = item.title
                                )
                            },
                            label = {
                                Text(
                                    text = item.title,
                                    fontWeight = if (selectedSection == item.index) FontWeight.Bold else FontWeight.Normal
                                )
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = MaterialTheme.colorScheme.onBackground,
                                selectedTextColor = MaterialTheme.colorScheme.onBackground,
                                unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(
                                    0.65f
                                ),
                                unselectedTextColor = MaterialTheme.colorScheme.onBackground.copy(
                                    0.65f
                                ),
                                indicatorColor = MaterialTheme.colorScheme.background.copy(0.75f)
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
            ) {
                ScrollableContent(
                    animatedVisibilityScope = animatedVisibilityScope,
                    scrollState = scrollState,
                    sectionPositions = sectionPositions,
                    hazeState = hazeState
                )
            }
        }

        UpdateSelectedSection(
            scrollState = scrollState,
            sectionPositions = sectionPositions,
            onSectionChange = { selectedSection = it }
        )
    }
}
