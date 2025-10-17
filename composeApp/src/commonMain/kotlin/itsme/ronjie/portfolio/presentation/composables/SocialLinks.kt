package itsme.ronjie.portfolio.presentation.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowWidthSizeClass
import itsme.ronjie.portfolio.data.PortfolioData
import itsme.ronjie.portfolio.domain.model.ContactType

@Composable
fun SocialLinks() {
    val contacts = PortfolioData.contacts
    val socialContacts = contacts.filter { it.type != ContactType.EMAIL }
    val uriHandler = LocalUriHandler.current
    val adaptiveInfo = currentWindowAdaptiveInfo()
    val windowSizeClass = adaptiveInfo.windowSizeClass

    when (windowSizeClass.windowWidthSizeClass) {
        WindowWidthSizeClass.COMPACT -> {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                socialContacts.forEach { contact ->
                    IconButton(
                        onClick = { contact.url?.let { url -> uriHandler.openUri(url) } }
                    ) {
                        Icon(
                            imageVector = contact.icon,
                            contentDescription = contact.title,
                            modifier = Modifier.size(30.dp),
                            tint = contact.color
                        )
                    }
                }
            }
        }

        else -> {
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                socialContacts.forEach { contact ->
                    IconButton(
                        onClick = { contact.url?.let { url -> uriHandler.openUri(url) } }
                    ) {
                        Icon(
                            imageVector = contact.icon,
                            contentDescription = contact.title,
                            modifier = Modifier.size(30.dp),
                            tint = contact.color
                        )
                    }
                }
            }
        }
    }
}
