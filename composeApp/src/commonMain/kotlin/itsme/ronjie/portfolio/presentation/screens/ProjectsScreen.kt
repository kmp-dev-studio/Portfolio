package itsme.ronjie.portfolio.presentation.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
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


                Text(
                    text = "Showing ${projects.size} of $totalProjectsCount projects",
                    fontSize = 13.sp,
                    color = extendedColors.textSecondary,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )

                Spacer(Modifier.height(16.dp))

                if (totalProjectsCount > featuredProjectsLimit) {
                    Spacer(Modifier.height(8.dp))

                    Text(
                        text = "View All Projects",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = extendedColors.textPrimary,
                        modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
                    )

                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        githubOrganizations.forEach { org ->
                            OutlinedButton(
                                onClick = { openUrl("https://github.com/${org.id}") },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = org.color.copy(alpha = 0.2f),
                                    contentColor = org.color
                                ),
                                border = BorderStroke(1.dp, org.color),
                                shape = MaterialTheme.shapes.small
                            ) {
                                BasicText(
                                    text = org.displayName,
                                    style = TextStyle(
                                        color = org.color,
                                        fontWeight = FontWeight.Medium
                                    ),
                                    maxLines = 1,
                                    autoSize = TextAutoSize.StepBased(
                                        minFontSize = 11.sp,
                                        maxFontSize = 16.sp
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
