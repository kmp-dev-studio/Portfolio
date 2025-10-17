package itsme.ronjie.portfolio.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import itsme.ronjie.portfolio.data.PortfolioData
import itsme.ronjie.portfolio.domain.model.ContactType
import itsme.ronjie.portfolio.presentation.composables.InfoCard
import itsme.ronjie.portfolio.presentation.theme.extended
import itsme.ronjie.portfolio.presentation.utils.encodeURLParameter

@Composable
fun ContactScreen() {
    val extendedColors = MaterialTheme.colorScheme.extended
    val availability = PortfolioData.Profile.availability
    val uriHandler = LocalUriHandler.current

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

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

        OutlinedTextField(
            value = fullName,
            onValueChange = { fullName = it },
            label = { Text("Full Name") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = "Full Name"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            ),
            shape = MaterialTheme.shapes.small
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it.trim() },
            label = { Text("Email") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Email,
                    contentDescription = "Email"
                )
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            shape = MaterialTheme.shapes.small
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = message,
            onValueChange = { message = it },
            label = { Text("Message") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            maxLines = 6,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            shape = MaterialTheme.shapes.small
        )

        Spacer(Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(
                onClick = {
                    val subject = "Contact from $fullName"
                    val body = "Name: $fullName\nEmail: $email\n\nMessage:\n$message"
                    val encodedSubject = subject.encodeURLParameter()
                    val encodedBody = body.encodeURLParameter()
                    val email = PortfolioData.contacts.first { it.type == ContactType.EMAIL }.value
                    val mailtoUrl = "mailto:${email}?subject=$encodedSubject&body=$encodedBody"
                    uriHandler.openUri(mailtoUrl)
                },
                enabled = fullName.isNotBlank() && email.isNotBlank() && message.isNotBlank(),
                shape = MaterialTheme.shapes.small
            ) { Text("Send Message") }
        }

        Spacer(Modifier.height(32.dp))

        InfoCard(
            title = "Available For",
            icon = Icons.Filled.CheckCircle
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                availability.forEach { item ->
                    Text(
                        text = "• $item",
                        color = extendedColors.textSecondary
                    )
                }
            }
        }
    }
}
