package itsme.ronjie.portfolio.presentation.screens.splash

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit

internal data class TextFragment(
    val id: String,
    val text: String,
    val fontSize: TextUnit,
    val fontWeight: FontWeight,
    val x: Float,
    val y: Float,
    val color: Color,
    val targetType: FragmentTarget
)

internal enum class FragmentTarget {
    TITLE,
    BIO
}

internal sealed class DecorativeElement {
    abstract val x: Float
    abstract val y: Float

    data class IconElement(
        val icon: ImageVector,
        override val x: Float,
        override val y: Float
    ) : DecorativeElement()

    data class MorphingShapeElement(
        val shape: ShapeType,
        override val x: Float,
        override val y: Float
    ) : DecorativeElement()

    data class ChipElement(
        val text: String,
        override val x: Float,
        override val y: Float
    ) : DecorativeElement()

    data class ExpressiveCardElement(
        override val x: Float,
        override val y: Float
    ) : DecorativeElement()

    data class FloatingDotElement(
        override val x: Float,
        override val y: Float
    ) : DecorativeElement()

    data class CircularProgressElement(
        override val x: Float,
        override val y: Float
    ) : DecorativeElement()

    data class LinearProgressElement(
        override val x: Float,
        override val y: Float
    ) : DecorativeElement()
}

internal enum class ShapeType {
    CIRCLE,
    SQUARE,
    ROUNDED
}