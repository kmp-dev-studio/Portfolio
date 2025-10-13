package itsme.ronjie.portfolio.presentation.theme

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val iOSBlue = Color(0xFF007AFF)
val androidGreen = Color(0xFF3DDC84)
val kmpPurple = Color(0xFF7F52FF)
val toolsOrange = Color(0xFFFF7043)

// Background Colors
val darkBackground = Color(0xFF121212)
val surface = Color(0xFF1E1E1E)
val cardBackground = Color(0xFF2C2C2E)
val elevatedSurface = Color(0xFF2D2D2D)

// Text Colors
val textPrimary = Color(0xFFFFFFFF)
val textSecondary = Color(0xFFB3B3B3)
val textTertiary = Color(0xFF8E8E93)
val textDisabled = Color(0x61FFFFFF)

// Status Colors
val successGreen = Color(0xFF34C759)
val warningYellow = Color(0xFFFFCC00)
val errorRed = Color(0xFFFF3B30)
val infoBlue = Color(0xFF007AFF)

// Social Media Colors
val gitHubPurple = Color(0xFF6e5494)
val linkedInBlue = Color(0xFF0077B5)
val twitterBlue = Color(0xFF1DA1F2)
val instagramPink = Color(0xFFE1306C)

// UI Element Colors
val divider = Color(0x1FFFFFFF)
val outline = Color(0x33FFFFFF)
val ripple = Color(0x1FFFFFFF)
val overlay = Color(0x52000000)

// Gradient colors
val gradientStart = Color(0xFF1a1a2e)
val gradientMiddle = Color(0xFF16213e)
val gradientEnd = Color(0xFF0f3460)

@Immutable
data class ExtendedColors(
    // Primary Colors
    val iOSBlue: Color = Color(0xFF007AFF),
    val androidGreen: Color = Color(0xFF3DDC84),
    val kmpPurple: Color = Color(0xFF7F52FF),

    // Background Colors
    val darkBackground: Color = Color(0xFF121212),
    val surface: Color = Color(0xFF1E1E1E),
    val cardBackground: Color = Color(0xFF2C2C2E),
    val elevatedSurface: Color = Color(0xFF2D2D2D),

    // Text Colors
    val textPrimary: Color = Color(0xFFFFFFFF),
    val textSecondary: Color = Color(0xFFB3B3B3),
    val textTertiary: Color = Color(0xFF8E8E93),
    val textDisabled: Color = Color(0x61FFFFFF),

    // Status Colors
    val success: Color = Color(0xFF34C759),
    val warning: Color = Color(0xFFFFCC00),
    val error: Color = Color(0xFFFF3B30),
    val info: Color = Color(0xFF007AFF),

    // Social Media Colors
    val gitHubPurple: Color = Color(0xFF6e5494),
    val linkedInBlue: Color = Color(0xFF0077B5),
    val twitterBlue: Color = Color(0xFF1DA1F2),
    val instagramPink: Color = Color(0xFFE1306C),
    val fitnessRed: Color = Color(0xFFFF2D55),
    val toolsOrange: Color = Color(0xFFFF9500),

    // UI Element Colors
    val divider: Color = Color(0x1FFFFFFF),
    val outline: Color = Color(0x33FFFFFF),
    val ripple: Color = Color(0x1FFFFFFF),
    val overlay: Color = Color(0x52000000),

    // Gradient Colors
    val gradientStart: Color = Color(0xFF1a1a2e),
    val gradientMiddle: Color = Color(0xFF16213e),
    val gradientEnd: Color = Color(0xFF0f3460)
)

val LocalExtendedColors = staticCompositionLocalOf { ExtendedColors() }

val ColorScheme.extended: ExtendedColors
    @Composable
    get() = LocalExtendedColors.current
