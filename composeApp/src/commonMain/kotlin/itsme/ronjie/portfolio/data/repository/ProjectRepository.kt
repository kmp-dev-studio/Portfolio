package itsme.ronjie.portfolio.data.repository

import itsme.ronjie.portfolio.data.PortfolioData
import itsme.ronjie.portfolio.data.remote.GitHubApiService
import itsme.ronjie.portfolio.data.remote.dto.GitHubRepoDto
import itsme.ronjie.portfolio.domain.model.Platform
import itsme.ronjie.portfolio.domain.model.Project

class ProjectRepository(
    private val gitHubApiService: GitHubApiService = GitHubApiService()
) {
    suspend fun getProjects(githubUsername: String): List<Project> {
        val githubRepos = gitHubApiService.getUserRepos(githubUsername)

        val filteredRepos = githubRepos.filter { !it.fork && !it.archived }

        return filteredRepos.map { repo ->
            repo.toProject()
        }
    }

    suspend fun getProjectsFromMultipleOrgs(
        githubUsernames: List<String>,
        limit: Int? = null
    ): List<Project> {
        val allRepos = mutableListOf<GitHubRepoDto>()

        githubUsernames.forEach { username ->
            try {
                val repos = gitHubApiService.getUserRepos(username)
                val filteredRepos = repos.filter { repo ->
                    !repo.fork &&
                            !repo.archived &&
                            !isSpecialRepo(repo.name)
                }
                allRepos.addAll(filteredRepos)
            } catch (e: Exception) {
                println("Error fetching repos from $username: ${e.message}")
            }
        }

        val sorted = allRepos.sortedWith(
            compareByDescending<GitHubRepoDto> { it.stargazersCount }
                .thenByDescending { it.updatedAt }
        )

        val limited = if (limit != null) sorted.take(limit) else sorted

        return limited.map { it.toProject() }
    }

    private fun isSpecialRepo(repoName: String): Boolean {
        val specialRepoNames = listOf(
            ".github",
            ".github-private",
            "profile",
            "readme"
        )
        return specialRepoNames.any {
            repoName.equals(it, ignoreCase = true)
        }
    }

    private fun GitHubRepoDto.toProject(): Project {
        val platform = determinePlatform()

        return Project(
            id = id.toString(),
            title = name.replace("-", " ").split(" ").joinToString(" ") { word ->
                word.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
            },
            description = description ?: "A GitHub project",
            platform = platform,
            technologies = buildTechnologiesList(),
            githubUrl = htmlUrl,
            demoUrl = homepage,
            color = platform.color
        )
    }

    private fun GitHubRepoDto.determinePlatform(): Platform {
        val ownerName = this.owner.login.lowercase()

        return when {
            ownerName.contains("android-dev") -> PortfolioData.platforms[1]
            ownerName.contains("apple-dev") || ownerName.contains("ios-dev") -> PortfolioData.platforms[0]
            ownerName.contains("kmp-dev") -> PortfolioData.platforms[2]
            topics.any { it.contains("android", ignoreCase = true) } -> PortfolioData.platforms[1]
            topics.any { it.contains("ios", ignoreCase = true) } -> PortfolioData.platforms[0]
            topics.any {
                it.contains(
                    "kmp",
                    ignoreCase = true
                ) || it.contains("kotlin-multiplatform", ignoreCase = true)
            } -> PortfolioData.platforms[2]

            language?.equals("Kotlin", ignoreCase = true) == true -> PortfolioData.platforms[1]
            language?.equals("Swift", ignoreCase = true) == true -> PortfolioData.platforms[0]
            name.contains("android", ignoreCase = true) -> PortfolioData.platforms[1]
            name.contains("ios", ignoreCase = true) -> PortfolioData.platforms[0]
            name.contains("kmp", ignoreCase = true) -> PortfolioData.platforms[2]
            else -> PortfolioData.platforms[2]
        }
    }

    private fun GitHubRepoDto.buildTechnologiesList(): List<String> {
        val techs = mutableListOf<String>()

        language?.let { techs.add(it) }

        val techTopics = topics.filter { topic ->
            !topic.contains("portfolio", ignoreCase = true) &&
                    !topic.contains("project", ignoreCase = true) &&
                    !topic.contains("demo", ignoreCase = true)
        }
        techs.addAll(techTopics.take(5))

        return techs.distinct()
    }

    fun close() {
        gitHubApiService.close()
    }
}