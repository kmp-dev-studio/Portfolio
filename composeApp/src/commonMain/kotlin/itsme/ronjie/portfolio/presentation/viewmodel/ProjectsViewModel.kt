package itsme.ronjie.portfolio.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import itsme.ronjie.portfolio.data.PortfolioData
import itsme.ronjie.portfolio.data.repository.ProjectRepository
import itsme.ronjie.portfolio.domain.model.Project
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
        "android-dev-studio",
        "ios-dev-studio",
        "backend-dev-studio",
        "kmp-dev-studio"
    )

    fun loadProjects(featuredLimit: Int = 5) {
        viewModelScope.launch {
            try {
                _uiState.value = _uiState.value.copy(isLoading = true, errorMessage = null)

                val allProjects = repository.getProjectsFromMultipleOrgs(githubOrganizations)
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

    fun getOrganizations(): List<String> = githubOrganizations

    override fun onCleared() {
        super.onCleared()
        repository.close()
    }
}
