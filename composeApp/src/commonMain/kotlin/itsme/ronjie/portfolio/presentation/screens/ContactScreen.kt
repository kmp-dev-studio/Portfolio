package itsme.ronjie.portfolio.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import itsme.ronjie.portfolio.data.PortfolioData
import itsme.ronjie.portfolio.presentation.composables.ContactItem
import itsme.ronjie.portfolio.presentation.composables.InfoCard
import itsme.ronjie.portfolio.presentation.theme.extended

@Composable
fun ContactScreen() {
    val extendedColors = MaterialTheme.colorScheme.extended
    val contacts = PortfolioData.contacts
    val availability = PortfolioData.Profile.availability

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Get In Touch",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = extendedColors.textPrimary
        )

        Spacer(Modifier.height(32.dp))

        val uriHandler = LocalUriHandler.current
        contacts.forEach { contact ->
            ContactItem(
                icon = contact.icon,
                title = contact.title,
                value = contact.value,
                color = contact.color,
                onClick = { contact.url?.let { url -> uriHandler.openUri(url) } }
            )
            Spacer(Modifier.height(16.dp))
        }

        Spacer(Modifier.height(16.dp))

        InfoCard(
            title = "Available For",
            icon = Icons.Filled.CheckCircle
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                availability.forEach { item ->
                    Text("• $item", color = extendedColors.textSecondary)
                }
            }
        }
    }
}
