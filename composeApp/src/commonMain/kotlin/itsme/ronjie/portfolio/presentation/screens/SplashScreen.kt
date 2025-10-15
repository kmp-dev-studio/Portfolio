@file:OptIn(ExperimentalSharedTransitionApi::class, ExperimentalHazeMaterialsApi::class)

package itsme.ronjie.portfolio.presentation.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.hazeChild
import dev.chrisbanes.haze.materials.ExperimentalHazeMaterialsApi
import dev.chrisbanes.haze.materials.HazeMaterials
import itsme.ronjie.portfolio.data.PortfolioData
import itsme.ronjie.portfolio.presentation.composables.PlatformBadge
import itsme.ronjie.portfolio.presentation.screens.splash.DecorativeElementRenderer
import itsme.ronjie.portfolio.presentation.screens.splash.generateDecorativeElements
import itsme.ronjie.portfolio.presentation.screens.splash.generatePlatformPositions
import itsme.ronjie.portfolio.presentation.screens.splash.generateTextFragments
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SharedTransitionScope.SplashScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onSplashComplete: () -> Unit
) {
    val profile = PortfolioData.Profile
    val platforms = PortfolioData.platforms

    val textFragments = remember { generateTextFragments() }
    val platformPositions = remember { generatePlatformPositions() }
    val decorativeElements = remember { generateDecorativeElements() }

    val fullName = profile.NAME
    var typedText by remember { mutableStateOf("") }
    var showCursor by remember { mutableStateOf(true) }

    val alphaAnimation = remember { Animatable(0.25f) }
    val hazeState = remember { HazeState() }

    LaunchedEffect(Unit) {
        val typingDuration = fullName.length * 100L

        launch {
            alphaAnimation.animateTo(
                targetValue = 0.7f,
                animationSpec = tween(
                    durationMillis = typingDuration.toInt(),
                    easing = FastOutSlowInEasing
                )
            )
        }

        fullName.forEachIndexed { index, char ->
            typedText = fullName.substring(0, index + 1)
            delay(100)
        }

        repeat(3) {
            showCursor = false
            delay(300)
            showCursor = true
            delay(300)
        }

        alphaAnimation.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 800,
                easing = FastOutSlowInEasing
            )
        )

        onSplashComplete()
    }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val maxWidth = maxWidth
        val maxHeight = maxHeight

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
            ) {
                val nameWords = fullName.split(" ")
                nameWords.forEachIndexed { index, word ->
                    val startIndex = nameWords.take(index).sumOf { it.length + 1 }
                    val visibleChars = (typedText.length - startIndex).coerceIn(0, word.length)
                    val displayText = word.take(visibleChars)

                    Text(
                        text = displayText + if (index == nameWords.lastIndex && visibleChars == word.length && showCursor) "|" else "",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        maxLines = 1,
                        modifier = Modifier
                            .sharedElement(
                                rememberSharedContentState(key = "name_$index"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 500, easing = FastOutSlowInEasing)
                                }
                            )
                            .skipToLookaheadSize()
                    )
                }
            }
        }

        textFragments.forEach { fragment ->
            Text(
                text = fragment.text,
                fontSize = fragment.fontSize,
                fontWeight = fragment.fontWeight,
                color = fragment.color,
                maxLines = 1,
                modifier = Modifier
                    .offset(
                        x = maxWidth * fragment.x,
                        y = maxHeight * fragment.y
                    )
                    .hazeSource(state = hazeState)
                    .alpha(alphaAnimation.value)
                    .sharedElement(
                        rememberSharedContentState(key = fragment.id),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 500, easing = FastOutSlowInEasing)
                        }
                    )
                    .skipToLookaheadSize()
            )
        }

        platformPositions.forEach { (platformId, x, y) ->
            val platform = platforms.find { it.id == platformId }!!
            PlatformBadge(
                text = platform.name,
                color = platform.color,
                icon = platform.icon,
                modifier = Modifier
                    .offset(
                        x = maxWidth * x,
                        y = maxHeight * y
                    )
                    .hazeSource(state = hazeState)
                    .alpha(alphaAnimation.value)
                    .sharedElement(
                        rememberSharedContentState(key = "platform_badge_$platformId"),
                        animatedVisibilityScope = animatedVisibilityScope,
                        boundsTransform = { _, _ ->
                            tween(durationMillis = 500, easing = FastOutSlowInEasing)
                        }
                    )
                    .skipToLookaheadSize()
            )
        }

        val infiniteTransition = rememberInfiniteTransition(label = "morphing")

        decorativeElements.forEachIndexed { index, element ->
            Box(
                modifier = Modifier
                    .offset(
                        x = maxWidth * element.x,
                        y = maxHeight * element.y
                    )
                    .hazeSource(state = hazeState)
            ) {
                DecorativeElementRenderer(
                    element = element,
                    index = index,
                    infiniteTransition = infiniteTransition,
                    alphaValue = alphaAnimation.value,
                    maxWidth = maxWidth,
                    maxHeight = maxHeight
                )
            }
        }
    }
}