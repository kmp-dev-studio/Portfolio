package itsme.ronjie.portfolio.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Work
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
import itsme.ronjie.portfolio.presentation.theme.androidGreen
import itsme.ronjie.portfolio.presentation.theme.gitHubPurple
import itsme.ronjie.portfolio.presentation.theme.iOSBlue
import itsme.ronjie.portfolio.presentation.theme.kmpPurple
import itsme.ronjie.portfolio.presentation.theme.linkedInBlue
import itsme.ronjie.portfolio.presentation.theme.toolsOrange

object PortfolioData {

    val platforms = listOf(
        Platform(
            id = "ios",
            name = "iOS",
            icon = SimpleIcons.Apple,
            color = iOSBlue
        ),
        Platform(
            id = "android",
            name = "Android",
            icon = SimpleIcons.Android,
            color = androidGreen
        ),
        Platform(
            id = "kmp",
            name = "KMP",
            icon = SimpleIcons.Kotlin,
            color = kmpPurple
        )
    )

    val projects = listOf(
        Project(
            id = "gweather",
            title = "GWeather",
            description = "Beautiful weather app with real-time forecasts, built with Jetpack Compose and OpenWeather API",
            platform = platforms[1],
            technologies = listOf("Kotlin", "Jetpack Compose", "OpenWeather", "Core Location"),
            color = platforms[1].color,
            githubUrl = "https://github.com/android-dev-studio/GWeather"
        )
    )

    val skillCategories = listOf(
        SkillCategory(
            id = "ios",
            title = "iOS Development",
            color = iOSBlue,
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
            color = androidGreen,
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
            color = kmpPurple,
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
            color = toolsOrange,
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
            color = iOSBlue,
            url = "mailto:manon.ronjiediafante@gmail.com"
        ),
        Contact(
            id = "github",
            type = ContactType.GITHUB,
            title = "GitHub",
            value = "github.com/itsmeronjie",
            icon = FontAwesomeIcons.Brands.Github,
            color = gitHubPurple,
            url = "https://github.com/itsmeronjie"
        ),
        Contact(
            id = "linkedin",
            type = ContactType.LINKEDIN,
            title = "LinkedIn",
            value = "linkedin.com/in/ronjiemanon",
            icon = FontAwesomeIcons.Brands.Linkedin,
            color = linkedInBlue,
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
