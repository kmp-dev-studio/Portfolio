package itsme.ronjie.portfolio.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf

enum class ThemeMode {
    LIGHT,
    DARK,
    SYSTEM
}

class ThemeManager(initialTheme: ThemeMode = ThemeMode.DARK) {
    val currentTheme: MutableState<ThemeMode> = mutableStateOf(initialTheme)

    fun toggleTheme() {
        currentTheme.value = when (currentTheme.value) {
            ThemeMode.LIGHT -> ThemeMode.DARK
            ThemeMode.DARK -> ThemeMode.LIGHT
            ThemeMode.SYSTEM -> ThemeMode.LIGHT
        }
    }

    fun setTheme(theme: ThemeMode) {
        currentTheme.value = theme
    }

    fun isDarkMode(): Boolean {
        return currentTheme.value == ThemeMode.DARK
    }
}

val LocalThemeManager = staticCompositionLocalOf<ThemeManager> {
    ThemeManager()
}

@Composable
fun rememberThemeManager(initialTheme: ThemeMode = ThemeMode.DARK): ThemeManager {
    return remember { ThemeManager(initialTheme) }
}