package itsme.ronjie.portfolio.presentation.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import itsme.ronjie.portfolio.presentation.theme.LocalThemeManager

@Composable
fun ThemeToggleButton(
    modifier: Modifier = Modifier
) {
    val themeManager = LocalThemeManager.current
    val isDarkMode = themeManager.isDarkMode()
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val isCompact =
        adaptiveInfo.windowSizeClass.windowWidthSizeClass == WindowWidthSizeClass.COMPACT

    val rotation by animateFloatAsState(
        targetValue = if (isDarkMode) 0f else 180f,
        animationSpec = tween(durationMillis = 300),
        label = "theme_icon_rotation"
    )

    IconButton(
        onClick = { themeManager.toggleTheme() },
        modifier = modifier
    ) {
        Icon(
            imageVector = if (isDarkMode) Icons.Filled.DarkMode else Icons.Filled.LightMode,
            contentDescription = if (isDarkMode) "Switch to Light Mode" else "Switch to Dark Mode",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(24.dp)
                .rotate(rotation)
        )
    }

    if (!isCompact) {
        HorizontalDivider(
            modifier = Modifier.width(40.dp),
            thickness = 0.75.dp,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.75f)
        )
    }
}
