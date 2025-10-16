package itsme.ronjie.portfolio.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val iOSBlue = Color(0xFF007AFF)
val androidGreen = Color(0xFF839E2E)
val kmpPurple = Color(0xFF7F52FF)
val toolsOrange = Color(0xFFFF7043)
val darkBackground = Color(0xFF121212)
val cardBackground = Color(0xFF2C2C2E)
val gitHubPurple = Color(0xFF6e5494)
val linkedInBlue = Color(0xFF0077B5)

data class ExtendedColors(
    val darkBackground: Color = Color(0xFF121212),
    val surface: Color = Color(0xFF1E1E1E),
    val cardBackground: Color = Color(0xFF2C2C2E),
    val elevatedSurface: Color = Color(0xFF2D2D2D),

    val textPrimary: Color = Color(0xFFFFFFFF),
    val textSecondary: Color = Color(0xFFB3B3B3),
    val textTertiary: Color = Color(0xFF8E8E93),
    val textDisabled: Color = Color(0x61FFFFFF),

    val success: Color = Color(0xFF34C759),
    val warning: Color = Color(0xFFFFCC00),
    val error: Color = Color(0xFFFF3B30),
    val info: Color = Color(0xFF007AFF),

    val gitHubPurple: Color = Color(0xFF6e5494),
    val linkedInBlue: Color = Color(0xFF0077B5),
    val twitterBlue: Color = Color(0xFF1DA1F2),
    val instagramPink: Color = Color(0xFFE1306C),
    val fitnessRed: Color = Color(0xFFFF2D55),
    val toolsOrange: Color = Color(0xFFFF9500),

    val divider: Color = Color(0x1FFFFFFF),
    val outline: Color = Color(0x33FFFFFF),
    val ripple: Color = Color(0x1FFFFFFF),
    val overlay: Color = Color(0x52000000),

    val gradientStart: Color = Color(0xFF1a1a2e),
    val gradientMiddle: Color = Color(0xFF16213e),
    val gradientEnd: Color = Color(0xFF0f3460)
)

fun lightExtendedColors() = ExtendedColors(
    darkBackground = Color(0xFFF5F5F5),
    surface = Color(0xFFFFFFFF),
    cardBackground = Color(0xFFFAFAFA),
    elevatedSurface = Color(0xFFFFFFFF),

    textPrimary = Color(0xFF000000),
    textSecondary = Color(0xFF5C5C5C),
    textTertiary = Color(0xFF8E8E93),
    textDisabled = Color(0x61000000),

    success = Color(0xFF34C759),
    warning = Color(0xFFFFCC00),
    error = Color(0xFFFF3B30),
    info = Color(0xFF007AFF),

    gitHubPurple = Color(0xFF6e5494),
    linkedInBlue = Color(0xFF0077B5),
    twitterBlue = Color(0xFF1DA1F2),
    instagramPink = Color(0xFFE1306C),
    fitnessRed = Color(0xFFFF2D55),
    toolsOrange = Color(0xFFFF9500),

    divider = Color(0x1F000000),
    outline = Color(0x33000000),
    ripple = Color(0x1F000000),
    overlay = Color(0x52FFFFFF),

    gradientStart = Color(0xFFE3F2FD),
    gradientMiddle = Color(0xFFBBDEFB),
    gradientEnd = Color(0xFF90CAF9)
)

fun darkExtendedColors() = ExtendedColors()

val LocalExtendedColors = staticCompositionLocalOf { ExtendedColors() }

val ColorScheme.extended: ExtendedColors
    @Composable
    get() = LocalExtendedColors.current
