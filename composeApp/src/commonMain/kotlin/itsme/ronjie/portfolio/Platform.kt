package itsme.ronjie.portfolio

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
