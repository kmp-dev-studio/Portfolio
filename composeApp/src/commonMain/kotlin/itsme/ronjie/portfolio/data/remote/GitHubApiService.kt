package itsme.ronjie.portfolio.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import itsme.ronjie.portfolio.data.remote.dto.GitHubRepoDto
import kotlinx.serialization.json.Json

class GitHubApiService {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    suspend fun getUserRepos(username: String): List<GitHubRepoDto> {
        return try {
            client.get("https://api.github.com/users/$username/repos") {
                parameter("sort", "updated")
                parameter("per_page", 100)
            }.body()
        } catch (e: Exception) {
            println("Error fetching repos: ${e.message}")
            emptyList()
        }
    }

    fun close() {
        client.close()
    }
}