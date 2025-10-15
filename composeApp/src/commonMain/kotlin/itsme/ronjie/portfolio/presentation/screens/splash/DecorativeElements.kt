package itsme.ronjie.portfolio.presentation.screens.splash

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import itsme.ronjie.portfolio.presentation.theme.extended

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
internal fun DecorativeElementRenderer(
    element: DecorativeElement,
    index: Int,
    infiniteTransition: InfiniteTransition,
    alphaValue: Float,
    maxWidth: Dp,
    maxHeight: Dp
) {
    val extendedColors = MaterialTheme.colorScheme.extended

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
                    .size(32.dp)
                    .rotate(rotation.value)
                    .alpha(alphaValue)
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
                    .alpha(alphaValue)
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
                    .scale(pulse.value)
                    .alpha(alphaValue)
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
                    .size(70.dp, 50.dp)
                    .alpha(alphaValue)
            ) {}
        }

        is DecorativeElement.FloatingDotElement -> {
            Box(
                modifier = Modifier
                    .size(8.dp)
                    .background(Color.White.copy(alpha = 0.5f), CircleShape)
                    .alpha(alphaValue)
            )
        }

        is DecorativeElement.CircularProgressElement -> {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .alpha(alphaValue)
            ) {
                CircularWavyProgressIndicator()
            }
        }

        is DecorativeElement.LinearProgressElement -> {
            Box(
                modifier = Modifier
                    .alpha(alphaValue)
            ) {
                LinearWavyProgressIndicator(
                    modifier = Modifier.size(100.dp, 4.dp)
                )
            }
        }
    }
}