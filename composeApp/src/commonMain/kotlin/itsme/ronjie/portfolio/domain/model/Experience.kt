package itsme.ronjie.portfolio.domain.model

import androidx.compose.ui.graphics.vector.ImageVector

data class Experience(
    val id: String,
    val title: String,
    val subtitle: String,
    val description: String? = null,
    val startDate: String? = null,
    val endDate: String? = null,
    val icon: ImageVector? = null,
    val technologies: List<String> = emptyList(),
    val isCurrentPosition: Boolean = false
)

data class WorkExperience(
    val id: String,
    val position: String,
    val company: String,
    val location: String? = null,
    val description: String,
    val startDate: String,
    val endDate: String? = null,
    val isCurrentPosition: Boolean = false,
    val responsibilities: List<String> = emptyList(),
    val achievements: List<String> = emptyList(),
    val technologies: List<String> = emptyList()
)
