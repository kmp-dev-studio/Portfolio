package itsme.ronjie.portfolio.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

// Mobile-themed color palette
val iOSBlue = Color(0xFF007AFF)
val androidGreen = Color(0xFF3DDC84)
val kmpPurple = Color(0xFF7F52FF)
val darkBackground = Color(0xFF1C1C1E)
val cardBackground = Color(0xFF2C2C2E)
val textPrimary = Color(0xFFFFFFFF)
val textSecondary = Color(0xFF8E8E93)

// Additional custom colors
val gitHubPurple = Color(0xFF6e5494)
val linkedInBlue = Color(0xFF0077B5)
val fitnessRed = Color(0xFFFF2D55)
val toolsOrange = Color(0xFFFF9500)

// Gradient colors
val gradientStart = Color(0xFF1a1a2e)
val gradientMiddle = Color(0xFF16213e)
val gradientEnd = Color(0xFF0f3460)

@Immutable
data class ExtendedColors(
    val iOSBlue: Color = Color(0xFF007AFF),
    val androidGreen: Color = Color(0xFF3DDC84),
    val kmpPurple: Color = Color(0xFF7F52FF),
    val gitHubPurple: Color = Color(0xFF6e5494),
    val linkedInBlue: Color = Color(0xFF0077B5),
    val fitnessRed: Color = Color(0xFFFF2D55),
    val toolsOrange: Color = Color(0xFFFF9500),
    val textPrimary: Color = Color(0xFFFFFFFF),
    val textSecondary: Color = Color(0xFF8E8E93),
    val gradientStart: Color = Color(0xFF1a1a2e),
    val gradientMiddle: Color = Color(0xFF16213e),
    val gradientEnd: Color = Color(0xFF0f3460)
)

val LocalExtendedColors = staticCompositionLocalOf { ExtendedColors() }

val ColorScheme.extended: ExtendedColors
    @Composable
    get() = LocalExtendedColors.current
