@file:OptIn(ExperimentalSharedTransitionApi::class)

package itsme.ronjie.portfolio.presentation.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
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
import itsme.ronjie.portfolio.data.PortfolioData
import itsme.ronjie.portfolio.presentation.composables.PlatformBadge
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun SharedTransitionScope.SplashScreen(
    animatedVisibilityScope: AnimatedVisibilityScope,
    onSplashComplete: () -> Unit
) {
    val profile = PortfolioData.Profile
    val platforms = PortfolioData.platforms

    val textFragments = remember {
        val title = profile.TITLE
        val bio = profile.BIO

        val titleWords = title.split(" ")
        val bioWords = bio.split(" ").chunked(3)

        val fragments = mutableListOf<TextFragment>()
        var id = 0

        titleWords.forEach { word ->
            fragments.add(
                TextFragment(
                    id = "title_${id++}",
                    text = word,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    x = Random.nextFloat(),
                    y = Random.nextFloat(),
                    color = Color.White.copy(alpha = 0.9f),
                    targetType = FragmentTarget.TITLE
                )
            )
        }

        bioWords.take(5).forEach { chunk ->
            fragments.add(
                TextFragment(
                    id = "bio_${id++}",
                    text = chunk.joinToString(" "),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    x = Random.nextFloat(),
                    y = Random.nextFloat(),
                    color = Color.White.copy(alpha = 0.7f),
                    targetType = FragmentTarget.BIO
                )
            )
        }

        fragments
    }

    val platformPositions = remember {
        platforms.map { platform ->
            Triple(
                platform.id,
                Random.nextFloat(),
                Random.nextFloat()
            )
        }
    }

    val fullName = profile.NAME
    var typedText by remember { mutableStateOf("") }
    var showCursor by remember { mutableStateOf(true) }

    val alphaAnimation = remember { Animatable(0.25f) }

    LaunchedEffect(Unit) {
        val typingDuration = fullName.length * 100L

        launch {
            alphaAnimation.animateTo(
                targetValue = 1f,
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

        delay(800)

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
    }
}

private data class TextFragment(
    val id: String,
    val text: String,
    val fontSize: androidx.compose.ui.unit.TextUnit,
    val fontWeight: FontWeight,
    val x: Float,
    val y: Float,
    val color: Color,
    val targetType: FragmentTarget
)

private enum class FragmentTarget {
    TITLE,
    BIO
}