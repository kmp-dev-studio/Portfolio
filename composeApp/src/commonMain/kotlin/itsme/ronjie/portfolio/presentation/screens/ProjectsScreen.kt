package itsme.ronjie.portfolio.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import itsme.ronjie.portfolio.presentation.composables.ProjectCard
import itsme.ronjie.portfolio.presentation.theme.extended
import itsme.ronjie.portfolio.presentation.viewmodel.ProjectsViewModel
import itsme.ronjie.portfolio.util.openUrl

@Composable
fun ProjectsScreen(
    viewModel: ProjectsViewModel = viewModel { ProjectsViewModel() },
    featuredProjectsLimit: Int = 5
) {
    val extendedColors = MaterialTheme.colorScheme.extended
    val uiState by viewModel.uiState.collectAsState()
    val projects = uiState.projects
    val isLoading = uiState.isLoading
    val errorMessage = uiState.errorMessage
    val totalProjectsCount = uiState.totalProjectsCount
    val githubOrganizations = viewModel.getOrganizations()

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

        when {
            isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            errorMessage != null -> {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(16.dp))
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

            else -> {
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

                if (totalProjectsCount > featuredProjectsLimit) {
                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "View All Projects by Organization",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = extendedColors.textPrimary,
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        githubOrganizations.forEach { org ->
                            OutlinedButton(
                                onClick = { openUrl("https://github.com/$org") },
                                modifier = Modifier.weight(1f)
                            ) {
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.OpenInNew,
                                        contentDescription = "Open $org",
                                        modifier = Modifier.height(16.dp)
                                    )
                                    Spacer(Modifier.height(4.dp))
                                    Text(
                                        text = run {
                                            org.split("-")
                                                .firstOrNull()
                                                ?.uppercase()
                                        } ?: org.uppercase(),
                                        fontSize = 11.sp,
                                        textAlign = TextAlign.Center,
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(16.dp))

                    Text(
                        text = "Showing ${projects.size} of $totalProjectsCount projects",
                        fontSize = 13.sp,
                        color = extendedColors.textSecondary,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}
