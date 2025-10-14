@file:OptIn(ExperimentalSharedTransitionApi::class)

package itsme.ronjie.portfolio.presentation.screens

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import itsme.ronjie.portfolio.data.PortfolioData
import itsme.ronjie.portfolio.presentation.composables.ExperienceItem
import itsme.ronjie.portfolio.presentation.composables.InfoCard
import itsme.ronjie.portfolio.presentation.composables.PlatformBadge
import itsme.ronjie.portfolio.presentation.theme.extended

@Composable
fun SharedTransitionScope.HomeScreen(
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val extendedColors = MaterialTheme.colorScheme.extended
    val platforms = PortfolioData.platforms
    val experiences = PortfolioData.experiences
    val profile = PortfolioData.Profile

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .background(
                    Brush.linearGradient(
                        colors = listOf(extendedColors.iOSBlue, extendedColors.androidGreen)
                    )
                )
                .border(4.dp, MaterialTheme.colorScheme.surface, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = profile.INITIALS,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth()
        ) {
            profile.NAME.split(" ").forEachIndexed { index, word ->
                Text(
                    text = word,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = extendedColors.textPrimary,
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

        Row(
            horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
            modifier = Modifier.fillMaxWidth()
        ) {
            profile.TITLE.split(" ").forEachIndexed { index, word ->
                Text(
                    text = word,
                    fontSize = 16.sp,
                    color = extendedColors.textSecondary,
                    maxLines = 1,
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "title_${profile.NAME.split(" ").size + index}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 500, easing = FastOutSlowInEasing)
                            }
                        )
                        .skipToLookaheadSize()
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            platforms.forEach { platform ->
                PlatformBadge(
                    text = platform.name,
                    color = platform.color,
                    icon = platform.icon,
                    modifier = Modifier
                        .sharedElement(
                            rememberSharedContentState(key = "platform_badge_${platform.id}"),
                            animatedVisibilityScope = animatedVisibilityScope,
                            boundsTransform = { _, _ ->
                                tween(durationMillis = 500, easing = FastOutSlowInEasing)
                            }
                        )
                        .skipToLookaheadSize()
                )
            }
        }

        Spacer(Modifier.height(32.dp))

        InfoCard(
            title = "About Me",
            icon = Icons.Filled.Person
        ) {
            val bioWords = profile.BIO.split(" ")
            val bioChunks = bioWords.chunked(3)
            val nameWordCount = profile.NAME.split(" ").size
            val titleWordCount = profile.TITLE.split(" ").size

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                bioChunks.take(5).forEachIndexed { index, chunk ->
                    Text(
                        text = chunk.joinToString(" ") + " ",
                        color = extendedColors.textSecondary,
                        lineHeight = 22.sp,
                        maxLines = 1,
                        modifier = Modifier
                            .sharedElement(
                                rememberSharedContentState(key = "bio_${nameWordCount + titleWordCount + index}"),
                                animatedVisibilityScope = animatedVisibilityScope,
                                boundsTransform = { _, _ ->
                                    tween(durationMillis = 500, easing = FastOutSlowInEasing)
                                }
                            )
                            .skipToLookaheadSize()
                    )
                }

                if (bioChunks.size > 5) {
                    Text(
                        text = bioChunks.drop(5).flatten().joinToString(" "),
                        color = extendedColors.textSecondary,
                        lineHeight = 22.sp
                    )
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        InfoCard(
            title = "Experience",
            icon = Icons.Filled.Work
        ) {
            experiences.forEachIndexed { index, experience ->
                ExperienceItem(experience.title, experience.subtitle)
                if (index < experiences.size - 1) {
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }
}
