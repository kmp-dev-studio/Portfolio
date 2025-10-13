package itsme.ronjie.portfolio.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Work
import androidx.compose.ui.graphics.Color
import compose.icons.FontAwesomeIcons
import compose.icons.SimpleIcons
import compose.icons.fontawesomeicons.Brands
import compose.icons.fontawesomeicons.brands.Github
import compose.icons.fontawesomeicons.brands.Linkedin
import compose.icons.simpleicons.Android
import compose.icons.simpleicons.Apple
import compose.icons.simpleicons.Gmail
import compose.icons.simpleicons.Kotlin
import itsme.ronjie.portfolio.domain.model.Contact
import itsme.ronjie.portfolio.domain.model.ContactType
import itsme.ronjie.portfolio.domain.model.Experience
import itsme.ronjie.portfolio.domain.model.Platform
import itsme.ronjie.portfolio.domain.model.ProficiencyLevel
import itsme.ronjie.portfolio.domain.model.Project
import itsme.ronjie.portfolio.domain.model.Skill
import itsme.ronjie.portfolio.domain.model.SkillCategory

object PortfolioData {

    val platforms = listOf(
        Platform(
            id = "ios",
            name = "iOS",
            icon = SimpleIcons.Apple,
            color = Color(0xFF007AFF)
        ),
        Platform(
            id = "android",
            name = "Android",
            icon = SimpleIcons.Android,
            color = Color(0xFF3DDC84)
        ),
        Platform(
            id = "kmp",
            name = "KMP",
            icon = SimpleIcons.Kotlin,
            color = Color(0xFF7F52FF)
        )
    )

    val projects = listOf(
        Project(
            id = "project1",
            title = "Android Weather App",
            description = "Beautiful weather app with real-time forecasts, built with Jetpack Compose and OpenWeather API",
            platform = platforms[1],
            technologies = listOf("Kotlin", "Jetpack Compose", "OpenWeather", "Core Location"),
            color = Color(0xFF007AFF),
            githubUrl = "https://github.com/android-dev-studio/GWeather"
        )
    )

    val skillCategories = listOf(
        SkillCategory(
            id = "ios",
            title = "iOS Development",
            color = Color(0xFF007AFF),
            skills = listOf(
                Skill(
                    id = "swift",
                    name = "Swift",
                    proficiencyLevel = ProficiencyLevel.EXPERT
                ),
                Skill(
                    id = "swiftui",
                    name = "SwiftUI",
                    proficiencyLevel = ProficiencyLevel.EXPERT
                ),
                Skill(
                    id = "uikit",
                    name = "UIKit",
                    proficiencyLevel = ProficiencyLevel.ADVANCED
                ),
                Skill(
                    id = "combine",
                    name = "Combine",
                    proficiencyLevel = ProficiencyLevel.ADVANCED
                ),
                Skill(
                    id = "coredata",
                    name = "Core Data",
                    proficiencyLevel = ProficiencyLevel.INTERMEDIATE
                ),
                Skill(
                    id = "xctest",
                    name = "XCTest",
                    proficiencyLevel = ProficiencyLevel.INTERMEDIATE
                )
            )
        ),
        SkillCategory(
            id = "android",
            title = "Android Development",
            color = Color(0xFF3DDC84),
            skills = listOf(
                Skill(
                    id = "kotlin",
                    name = "Kotlin",
                    proficiencyLevel = ProficiencyLevel.EXPERT
                ),
                Skill(
                    id = "compose",
                    name = "Jetpack Compose",
                    proficiencyLevel = ProficiencyLevel.EXPERT
                ),
                Skill(
                    id = "material",
                    name = "Material Design",
                    proficiencyLevel = ProficiencyLevel.ADVANCED
                ),
                Skill(
                    id = "coroutines",
                    name = "Coroutines",
                    proficiencyLevel = ProficiencyLevel.ADVANCED
                ),
                Skill(
                    id = "room",
                    name = "Room",
                    proficiencyLevel = ProficiencyLevel.ADVANCED
                ),
                Skill(
                    id = "viewmodel",
                    name = "ViewModel",
                    proficiencyLevel = ProficiencyLevel.EXPERT
                )
            )
        ),
        SkillCategory(
            id = "kmp",
            title = "Kotlin Multiplatform",
            color = Color(0xFF7F52FF),
            skills = listOf(
                Skill(
                    id = "kmp",
                    name = "KMP",
                    proficiencyLevel = ProficiencyLevel.ADVANCED
                ),
                Skill(
                    id = "cmp",
                    name = "Compose Multiplatform",
                    proficiencyLevel = ProficiencyLevel.ADVANCED
                ),
                Skill(
                    "shared",
                    name = "Shared Business Logic",
                    proficiencyLevel = ProficiencyLevel.ADVANCED
                ),
                Skill(
                    id = "common",
                    name = "Common Code",
                    proficiencyLevel = ProficiencyLevel.ADVANCED
                )
            )
        ),
        SkillCategory(
            id = "tools",
            title = "Tools & Others",
            color = Color(0xFFFF9500),
            skills = listOf(
                Skill(
                    id = "git",
                    name = "Git",
                    proficiencyLevel = ProficiencyLevel.EXPERT
                ),
                Skill(
                    id = "rest",
                    name = "REST APIs",
                    proficiencyLevel = ProficiencyLevel.EXPERT
                ),
                Skill(
                    id = "firebase",
                    name = "Firebase",
                    proficiencyLevel = ProficiencyLevel.ADVANCED
                ),
                Skill(
                    id = "xcode",
                    name = "Xcode",
                    proficiencyLevel = ProficiencyLevel.EXPERT
                ),
                Skill(
                    id = "studio",
                    name = "Android Studio",
                    proficiencyLevel = ProficiencyLevel.EXPERT
                ),
                Skill(
                    id = "cicd",
                    name = "CI/CD",
                    proficiencyLevel = ProficiencyLevel.INTERMEDIATE
                )
            )
        )
    )

    val contacts = listOf(
        Contact(
            id = "email",
            type = ContactType.EMAIL,
            title = "Email",
            value = "manon.ronjiediafante@gmail.com",
            icon = SimpleIcons.Gmail,
            color = Color(0xFF007AFF),
            url = "mailto:manon.ronjiediafante@gmail.com"
        ),
        Contact(
            id = "github",
            type = ContactType.GITHUB,
            title = "GitHub",
            value = "github.com/itsmeronjie",
            icon = FontAwesomeIcons.Brands.Github,
            color = Color(0xFF6e5494),
            url = "https://github.com/itsmeronjie"
        ),
        Contact(
            id = "linkedin",
            type = ContactType.LINKEDIN,
            title = "LinkedIn",
            value = "linkedin.com/in/ronjiemanon",
            icon = FontAwesomeIcons.Brands.Linkedin,
            color = Color(0xFF0077B5),
            url = "https://www.linkedin.com/in/ronjiemanon/"
        )
    )

    val experiences = listOf(
        Experience(
            id = "exp1",
            title = "iOS Development",
            subtitle = "Swift / SwiftUI",
            icon = Icons.Filled.Work
        ),
        Experience(
            id = "exp2",
            title = "Android Development",
            subtitle = "Kotlin / Jetpack Compose",
            icon = Icons.Filled.Work
        ),
        Experience(
            id = "exp3",
            title = "Cross-Platform",
            subtitle = "Kotlin Multiplatform",
            icon = Icons.Filled.Work
        )
    )

    object Profile {
        const val NAME = "Ronjie Man-on"
        const val TITLE = "Mobile Developer"
        const val BIO =
            "Passionate mobile developer specializing in native iOS development with Swift & SwiftUI, " +
                    "Android development with Kotlin & Jetpack Compose, and cross-platform development with " +
                    "Kotlin Multiplatform. I create beautiful, performant mobile experiences that delight users " +
                    "across all platforms."
        const val INITIALS = "RM"

        val availability = listOf(
            "Freelance Projects",
            "Contract Work",
            "Full-time Opportunities",
            "Technical Consulting"
        )
    }
}
