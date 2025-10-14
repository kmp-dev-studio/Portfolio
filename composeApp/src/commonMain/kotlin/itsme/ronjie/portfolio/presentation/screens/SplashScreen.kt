@file:OptIn(ExperimentalSharedTransitionApi::class)

package itsme.ronjie.portfolio.presentation.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearWavyProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import itsme.ronjie.portfolio.data.PortfolioData
import itsme.ronjie.portfolio.presentation.composables.PlatformBadge
import itsme.ronjie.portfolio.presentation.theme.extended
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

private fun generateRandomPosition(): Pair<Float, Float> {
    var x: Float
    var y: Float
    do {
        x = Random.nextFloat()
        y = Random.nextFloat()
    } while (x in 0.3f..0.7f && y in 0.35f..0.65f)
    return Pair(x, y)
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
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
            val (x, y) = generateRandomPosition()
            fragments.add(
                TextFragment(
                    id = "title_${id++}",
                    text = word,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Normal,
                    x = x,
                    y = y,
                    color = Color.White.copy(alpha = 0.9f),
                    targetType = FragmentTarget.TITLE
                )
            )
        }

        bioWords.take(5).forEach { chunk ->
            val (x, y) = generateRandomPosition()
            fragments.add(
                TextFragment(
                    id = "bio_${id++}",
                    text = chunk.joinToString(" "),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Light,
                    x = x,
                    y = y,
                    color = Color.White.copy(alpha = 0.7f),
                    targetType = FragmentTarget.BIO
                )
            )
        }

        fragments
    }

    val platformPositions = remember {
        platforms.map { platform ->
            val (x, y) = generateRandomPosition()
            Triple(
                platform.id,
                x,
                y
            )
        }
    }

    val decorativeElements = remember {
        listOf(
            DecorativeElement.IconElement(
                Icons.Default.Code,
                Random.nextFloat(),
                Random.nextFloat()
            ),
            DecorativeElement.IconElement(
                Icons.Default.Person,
                Random.nextFloat(),
                Random.nextFloat()
            ),
            DecorativeElement.IconElement(
                Icons.Default.Star,
                Random.nextFloat(),
                Random.nextFloat()
            ),
            DecorativeElement.IconElement(
                Icons.Default.Work,
                Random.nextFloat(),
                Random.nextFloat()
            ),
            DecorativeElement.MorphingShapeElement(
                ShapeType.CIRCLE,
                Random.nextFloat(),
                Random.nextFloat()
            ),
            DecorativeElement.MorphingShapeElement(
                ShapeType.SQUARE,
                Random.nextFloat(),
                Random.nextFloat()
            ),
            DecorativeElement.MorphingShapeElement(
                ShapeType.ROUNDED,
                Random.nextFloat(),
                Random.nextFloat()
            ),
            DecorativeElement.MorphingShapeElement(
                ShapeType.CIRCLE,
                Random.nextFloat(),
                Random.nextFloat()
            ),
            DecorativeElement.MorphingShapeElement(
                ShapeType.SQUARE,
                Random.nextFloat(),
                Random.nextFloat()
            ),
            DecorativeElement.MorphingShapeElement(
                ShapeType.ROUNDED,
                Random.nextFloat(),
                Random.nextFloat()
            ),
            DecorativeElement.ChipElement("Android", Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.ChipElement("iOS", Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.ChipElement("Web", Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.ChipElement("Desktop", Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.ChipElement("KMP", Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.ExpressiveCardElement(Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.ExpressiveCardElement(Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.ExpressiveCardElement(Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.ExpressiveCardElement(Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.FloatingDotElement(Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.FloatingDotElement(Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.FloatingDotElement(Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.FloatingDotElement(Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.FloatingDotElement(Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.CircularProgressElement(Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.CircularProgressElement(Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.LinearProgressElement(Random.nextFloat(), Random.nextFloat()),
            DecorativeElement.LinearProgressElement(Random.nextFloat(), Random.nextFloat())
        )
    }

    val fullName = profile.NAME
    var typedText by remember { mutableStateOf("") }
    var showCursor by remember { mutableStateOf(true) }

    val alphaAnimation = remember { Animatable(0.25f) }

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

        val extendedColors = MaterialTheme.colorScheme.extended
        val infiniteTransition = rememberInfiniteTransition(label = "morphing")

        decorativeElements.forEachIndexed { index, element ->
            when (element) {
                is DecorativeElement.IconElement -> {
                    val rotation = infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 360f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(4000 + index * 500, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Restart
                        ),
                        label = "icon_rotation_$index"
                    )
                    Icon(
                        imageVector = element.icon,
                        contentDescription = null,
                        tint = Color.White.copy(alpha = 0.6f),
                        modifier = Modifier
                            .offset(
                                x = maxWidth * element.x,
                                y = maxHeight * element.y
                            )
                            .size(32.dp)
                            .rotate(rotation.value)
                            .alpha(alphaAnimation.value)
                    )
                }

                is DecorativeElement.MorphingShapeElement -> {
                    val morphProgress = infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(2000 + index * 300, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "morph_$index"
                    )

                    val cornerRadius = when (element.shape) {
                        ShapeType.CIRCLE -> (20f * (1f - morphProgress.value)).dp
                        ShapeType.SQUARE -> (4f + 16f * morphProgress.value).dp
                        ShapeType.ROUNDED -> (16f + 4f * morphProgress.value).dp
                    }

                    val scale = 1f + 0.2f * morphProgress.value

                    Box(
                        modifier = Modifier
                            .offset(
                                x = maxWidth * element.x,
                                y = maxHeight * element.y
                            )
                            .size(40.dp)
                            .scale(scale)
                            .background(
                                extendedColors.iOSBlue.copy(alpha = 0.3f),
                                RoundedCornerShape(cornerRadius)
                            )
                            .border(
                                2.dp,
                                Color.White.copy(alpha = 0.4f),
                                RoundedCornerShape(cornerRadius)
                            )
                            .alpha(alphaAnimation.value)
                    )
                }

                is DecorativeElement.ChipElement -> {
                    val pulse = infiniteTransition.animateFloat(
                        initialValue = 1f,
                        targetValue = 1.1f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(1500 + index * 200, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "chip_pulse_$index"
                    )
                    Surface(
                        shape = RoundedCornerShape(20.dp),
                        color = extendedColors.androidGreen.copy(alpha = 0.3f),
                        modifier = Modifier
                            .offset(
                                x = maxWidth * element.x,
                                y = maxHeight * element.y
                            )
                            .scale(pulse.value)
                            .alpha(alphaAnimation.value)
                    ) {
                        Text(
                            text = element.text,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                }

                is DecorativeElement.ExpressiveCardElement -> {
                    val cardMorph = infiniteTransition.animateFloat(
                        initialValue = 12f,
                        targetValue = 24f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(2500 + index * 400, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "card_morph_$index"
                    )
                    Card(
                        shape = RoundedCornerShape(cardMorph.value.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.3f)
                        ),
                        modifier = Modifier
                            .offset(
                                x = maxWidth * element.x,
                                y = maxHeight * element.y
                            )
                            .size(70.dp, 50.dp)
                            .alpha(alphaAnimation.value)
                    ) {}
                }

                is DecorativeElement.FloatingDotElement -> {
                    val float = infiniteTransition.animateFloat(
                        initialValue = 0f,
                        targetValue = 20f,
                        animationSpec = infiniteRepeatable(
                            animation = tween(2000 + index * 300, easing = FastOutSlowInEasing),
                            repeatMode = RepeatMode.Reverse
                        ),
                        label = "float_$index"
                    )
                    Box(
                        modifier = Modifier
                            .offset(
                                x = maxWidth * element.x,
                                y = maxHeight * element.y + float.value.dp
                            )
                            .size(8.dp)
                            .background(Color.White.copy(alpha = 0.5f), CircleShape)
                            .alpha(alphaAnimation.value)
                    )
                }

                is DecorativeElement.CircularProgressElement -> {
                    Box(
                        modifier = Modifier
                            .offset(
                                x = maxWidth * element.x,
                                y = maxHeight * element.y
                            )
                            .size(48.dp)
                            .alpha(alphaAnimation.value)
                    ) {
                        CircularWavyProgressIndicator()
                    }
                }

                is DecorativeElement.LinearProgressElement -> {
                    Box(
                        modifier = Modifier
                            .offset(
                                x = maxWidth * element.x,
                                y = maxHeight * element.y
                            )
                            .alpha(alphaAnimation.value)
                    ) {
                        LinearWavyProgressIndicator(
                            modifier = Modifier.size(100.dp, 4.dp)
                        )
                    }
                }
            }
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

private sealed class DecorativeElement {
    data class IconElement(
        val icon: androidx.compose.ui.graphics.vector.ImageVector,
        val x: Float,
        val y: Float
    ) : DecorativeElement()

    data class MorphingShapeElement(
        val shape: ShapeType,
        val x: Float,
        val y: Float
    ) : DecorativeElement()

    data class ChipElement(
        val text: String,
        val x: Float,
        val y: Float
    ) : DecorativeElement()

    data class ExpressiveCardElement(
        val x: Float,
        val y: Float
    ) : DecorativeElement()

    data class FloatingDotElement(
        val x: Float,
        val y: Float
    ) : DecorativeElement()

    data class CircularProgressElement(
        val x: Float,
        val y: Float
    ) : DecorativeElement()

    data class LinearProgressElement(
        val x: Float,
        val y: Float
    ) : DecorativeElement()
}

private enum class ShapeType {
    CIRCLE,
    SQUARE,
    ROUNDED
}
