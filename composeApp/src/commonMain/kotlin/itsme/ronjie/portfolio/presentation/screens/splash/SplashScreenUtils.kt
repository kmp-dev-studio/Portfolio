package itsme.ronjie.portfolio.presentation.screens.splash

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import itsme.ronjie.portfolio.data.PortfolioData
import kotlin.random.Random

internal fun generateRandomPosition(): Pair<Float, Float> {
    var x: Float
    var y: Float
    do {
        x = Random.nextFloat()
        y = Random.nextFloat()
    } while (x in 0.3f..0.7f && y in 0.35f..0.65f)
    return Pair(x, y)
}

internal fun generateTextFragments(): List<TextFragment> {
    val profile = PortfolioData.Profile
    val title = profile.TITLE
    val bio = profile.BIO

    val titleWords = title.split(" ")
    val bioWords = bio.split(" ")

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

    bioWords.forEach { word ->
        val (x, y) = generateRandomPosition()
        fragments.add(
            TextFragment(
                id = "bio_${id++}",
                text = word,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light,
                x = x,
                y = y,
                color = Color.White.copy(alpha = 0.7f),
                targetType = FragmentTarget.BIO
            )
        )
    }

    return fragments
}

internal fun generatePlatformPositions(): List<Triple<String, Float, Float>> {
    val platforms = PortfolioData.platforms
    return platforms.map { platform ->
        val (x, y) = generateRandomPosition()
        Triple(platform.id, x, y)
    }
}

internal fun generateDecorativeElements(): List<DecorativeElement> {
    return buildList {
        repeat(4) {
            val (x, y) = generateRandomPosition()
            add(
                DecorativeElement.IconElement(
                    icon = when (it) {
                        0 -> Icons.Default.Code
                        1 -> Icons.Default.Person
                        2 -> Icons.Default.Star
                        else -> Icons.Default.Work
                    },
                    x = x,
                    y = y
                )
            )
        }
        repeat(6) {
            val (x, y) = generateRandomPosition()
            add(
                DecorativeElement.MorphingShapeElement(
                    shape = when (it % 3) {
                        0 -> ShapeType.CIRCLE
                        1 -> ShapeType.SQUARE
                        else -> ShapeType.ROUNDED
                    },
                    x = x,
                    y = y
                )
            )
        }
        listOf("Android", "iOS", "Web", "Desktop", "KMP").forEach { name ->
            val (x, y) = generateRandomPosition()
            add(DecorativeElement.ChipElement(name, x, y))
        }
        repeat(4) {
            val (x, y) = generateRandomPosition()
            add(DecorativeElement.ExpressiveCardElement(x, y))
        }
        repeat(5) {
            val (x, y) = generateRandomPosition()
            add(DecorativeElement.FloatingDotElement(x, y))
        }
        repeat(2) {
            val (x, y) = generateRandomPosition()
            add(DecorativeElement.CircularProgressElement(x, y))
        }
        repeat(2) {
            val (x, y) = generateRandomPosition()
            add(DecorativeElement.LinearProgressElement(x, y))
        }
    }
}