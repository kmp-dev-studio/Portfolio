package itsme.ronjie.portfolio.domain.model

import androidx.compose.ui.graphics.Color

data class Project(
    val id: String,
    val title: String,
    val description: String,
    val platform: Platform,
    val technologies: List<String>,
    val imageUrl: String? = null,
    val githubUrl: String? = null,
    val playStoreUrl: String? = null,
    val appStoreUrl: String? = null,
    val demoUrl: String? = null,
    val color: Color
)
