package itsme.ronjie.portfolio.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Skill(
    val id: String,
    val name: String,
    val icon: ImageVector? = null,
    val proficiencyLevel: ProficiencyLevel = ProficiencyLevel.INTERMEDIATE
)

data class SkillCategory(
    val id: String,
    val title: String,
    val color: Color,
    val icon: ImageVector? = null,
    val skills: List<Skill>
)

enum class ProficiencyLevel {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED,
    EXPERT
}
