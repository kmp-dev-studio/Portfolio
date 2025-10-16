package itsme.ronjie.portfolio.presentation.navigation

import androidx.compose.foundation.ScrollState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun UpdateSelectedSection(
    scrollState: ScrollState,
    sectionPositions: Map<Int, Float>,
    onSectionChange: (Int) -> Unit
) {
    LaunchedEffect(scrollState.value, scrollState.maxValue) {
        if (sectionPositions.isEmpty()) {
            onSectionChange(0)
            return@LaunchedEffect
        }

        val currentScroll = scrollState.value.toFloat()
        val maxScroll = scrollState.maxValue.toFloat()
        val isAtBottom = maxScroll > 0 && currentScroll >= maxScroll - 50f

        val section0End = (sectionPositions[0] ?: 0f) +
                ((sectionPositions[1] ?: maxScroll) - (sectionPositions[0] ?: 0f)) / 2
        val section1End = (sectionPositions[1] ?: 0f) +
                ((sectionPositions[2] ?: maxScroll) - (sectionPositions[1] ?: 0f)) / 2
        val section2End = (sectionPositions[2] ?: 0f) +
                ((sectionPositions[3] ?: maxScroll) - (sectionPositions[2] ?: 0f)) / 2

        val selected = when {
            isAtBottom -> 3
            currentScroll < section0End -> 0
            currentScroll < section1End -> 1
            currentScroll < section2End -> 2
            else -> 3
        }

        onSectionChange(selected)
    }
}
