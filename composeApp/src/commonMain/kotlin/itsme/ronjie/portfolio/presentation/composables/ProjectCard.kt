package itsme.ronjie.portfolio.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Github
import itsme.ronjie.portfolio.presentation.theme.cardBackground
import itsme.ronjie.portfolio.presentation.theme.extended
import itsme.ronjie.portfolio.presentation.theme.textPrimary
import itsme.ronjie.portfolio.presentation.theme.textSecondary

@Composable
fun ProjectCard(
    title: String,
    description: String,
    platform: String,
    color: Color,
    githubUrl: String? = null,
    onGithubClick: (() -> Unit)? = null
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(cardBackground)
    ) {
        Column(Modifier.padding(20.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(Modifier.weight(1f)) {
                    Text(
                        text = title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = textPrimary
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = description,
                        color = textSecondary,
                        fontSize = 14.sp,
                        lineHeight = 20.sp
                    )
                }

                if (githubUrl != null && onGithubClick != null) {
                    IconButton(
                        onClick = onGithubClick,
                        modifier = Modifier
                            .size(40.dp)
                            .background(
                                color = MaterialTheme.colorScheme.extended.gitHubPurple.copy(0.2f),
                                shape = RoundedCornerShape(8.dp)
                            )
                    ) {
                        Icon(
                            imageVector = FontAwesomeIcons.Brands.Github,
                            contentDescription = "GitHub",
                            tint = MaterialTheme.colorScheme.extended.gitHubPurple,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
            Spacer(Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .background(
                        color = color.copy(0.2f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = color.copy(0.5f),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 10.dp, vertical = 4.dp)
            ) {
                Text(
                    text = platform,
                    color = color,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
