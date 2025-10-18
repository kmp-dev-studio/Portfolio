package itsme.ronjie.portfolio.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GitHubOwner(
    val login: String,
    val id: Long
)

@Serializable
data class GitHubRepoDto(
    val id: Long,
    val name: String,
    val description: String? = null,
    val owner: GitHubOwner,
    @SerialName("html_url")
    val htmlUrl: String,
    val language: String? = null,
    val topics: List<String> = emptyList(),
    @SerialName("stargazers_count")
    val stargazersCount: Int = 0,
    @SerialName("forks_count")
    val forksCount: Int = 0,
    @SerialName("created_at")
    val createdAt: String,
    @SerialName("updated_at")
    val updatedAt: String,
    val homepage: String? = null,
    val fork: Boolean = false,
    val archived: Boolean = false
)