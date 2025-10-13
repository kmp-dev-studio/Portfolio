package itsme.ronjie.portfolio.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Platform(
    val id: String,
    val name: String,
    val icon: ImageVector,
    val color: Color
)

enum class PlatformType {
    IOS,
    ANDROID,
    KMP,
    WEB,
    DESKTOP
}
