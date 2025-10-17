package itsme.ronjie.portfolio.presentation.utils

fun String.encodeURLParameter(): String {
    return this.replace(" ", "%20")
        .replace("\n", "%0A")
        .replace(":", "%3A")
        .replace("@", "%40")
}

fun String.isValidEmail(): Boolean {
    if (this.isBlank()) return false
    val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
    return emailRegex.matches(this)
}
