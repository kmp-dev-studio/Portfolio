package itsme.ronjie.portfolio.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import itsme.ronjie.portfolio.presentation.theme.textPrimary
import itsme.ronjie.portfolio.presentation.theme.textSecondary

@Composable
fun ExperienceItem(
    role: String,
    tech: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = role,
            color = textPrimary,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = tech,
            color = textSecondary,
            fontSize = 14.sp
        )
    }
}
