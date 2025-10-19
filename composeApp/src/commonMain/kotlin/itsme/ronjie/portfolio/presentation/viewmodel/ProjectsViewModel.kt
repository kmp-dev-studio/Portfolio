package itsme.ronjie.portfolio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import itsme.ronjie.portfolio.data.PortfolioData
import itsme.ronjie.portfolio.data.repository.ProjectRepository
import itsme.ronjie.portfolio.domain.model.Organization
import itsme.ronjie.portfolio.domain.model.Project
import itsme.ronjie.portfolio.presentation.theme.androidGreen
import itsme.ronjie.portfolio.presentation.theme.iOSBlue
import itsme.ronjie.portfolio.presentation.theme.kmpPurple
import itsme.ronjie.portfolio.presentation.theme.toolsOrange
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class ProjectsUiState(
    val projects: List<Project> = emptyList(),
    val totalProjectsCount: Int = 0,
    val isLoading: Boolean = true,
    val errorMessage: String? = null
)

class ProjectsViewModel(
    private val repository: ProjectRepository = ProjectRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProjectsUiState())
    val uiState: StateFlow<ProjectsUiState> = _uiState.asStateFlow()

    private val githubOrganizations = listOf(
        Organization(
            id = "android-dev-studio",
            displayName = "Android",
            color = androidGreen
        ),
        Organization(
            id = "ios-dev-studio",
            displayName = "iOS",
            color = iOSBlue
        ),
        Organization(
            id = "kmp-dev-studio",
            displayName = "KMP",
            color = kmpPurple
        ),
        Organization(
            id = "backend-dev-studio",
            displayName = "Backend",
            color = toolsOrange
        )
    )

    fun loadProjects(featuredLimit: Int = 5) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

                val allProjects =
                    repository.getProjectsFromMultipleOrgs(githubOrganizations.map { it.id })
                val featuredProjects = allProjects.take(featuredLimit)

                _uiState.value = ProjectsUiState(
                    projects = featuredProjects,
                    totalProjectsCount = allProjects.size,
                    isLoading = false,
                    errorMessage = null
                )
            } catch (e: Exception) {
                _uiState.value = ProjectsUiState(
                    projects = PortfolioData.projects,
                    totalProjectsCount = PortfolioData.projects.size,
                    isLoading = false,
                    errorMessage = "Failed to load projects: ${e.message}"
                )
            }
        }
    }

    fun getOrganizations(): List<Organization> = githubOrganizations

    override fun onCleared() {
        super.onCleared()
        repository.close()
    }
}
