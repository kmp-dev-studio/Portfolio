package itsme.ronjie.portfolio.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

data class Contact(
    val id: String,
    val type: ContactType,
    val title: String,
    val value: String,
    val icon: ImageVector,
    val color: Color,
    val url: String? = null
)

enum class ContactType {
    EMAIL,
    PHONE,
    GITHUB,
    LINKEDIN,
    TWITTER,
    WEBSITE,
    OTHER
}
