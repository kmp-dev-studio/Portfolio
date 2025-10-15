package itsme.ronjie.portfolio.presentation.composables

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import itsme.ronjie.portfolio.presentation.theme.LocalThemeManager

@Composable
fun ThemeToggleButton(
    modifier: Modifier = Modifier
) {
    val themeManager = LocalThemeManager.current
    val isDarkMode = themeManager.isDarkMode()

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
            imageVector = if (isDarkMode) Icons.Default.LightMode else Icons.Default.DarkMode,
            contentDescription = if (isDarkMode) "Switch to Light Mode" else "Switch to Dark Mode",
            tint = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .size(24.dp)
                .rotate(rotation)
        )
    }
}