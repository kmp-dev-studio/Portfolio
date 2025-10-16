package itsme.ronjie.portfolio.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import itsme.ronjie.portfolio.data.PortfolioData
import itsme.ronjie.portfolio.presentation.composables.ProjectCard
import itsme.ronjie.portfolio.presentation.theme.extended
import itsme.ronjie.portfolio.util.openUrl

@Composable
fun ProjectsScreen() {
    val extendedColors = MaterialTheme.colorScheme.extended
    val projects = PortfolioData.projects

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Featured Projects",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = extendedColors.textPrimary
        )

        Spacer(Modifier.height(24.dp))

        projects.forEach { project ->
            ProjectCard(
                title = project.title,
                description = project.description,
                platform = project.platform.name,
                color = project.color,
                githubUrl = project.githubUrl,
                onGithubClick = {
                    project.githubUrl?.let { url ->
                        openUrl(url)
                    }
                }
            )
            Spacer(Modifier.height(16.dp))
        }
    }
}
