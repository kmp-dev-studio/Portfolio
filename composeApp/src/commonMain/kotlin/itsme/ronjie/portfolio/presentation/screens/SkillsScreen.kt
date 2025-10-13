package itsme.ronjie.portfolio.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import itsme.ronjie.portfolio.data.PortfolioData
import itsme.ronjie.portfolio.presentation.composables.SkillCategory
import itsme.ronjie.portfolio.presentation.theme.extended

@Composable
fun SkillsScreen() {
    val extendedColors = MaterialTheme.colorScheme.extended
    val skillCategories = PortfolioData.skillCategories

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Technical Skills",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = extendedColors.textPrimary
        )

        Spacer(Modifier.height(24.dp))

        skillCategories.forEach { category ->
            SkillCategory(
                title = category.title,
                color = category.color,
                skills = category.skills.map { it.name }
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
