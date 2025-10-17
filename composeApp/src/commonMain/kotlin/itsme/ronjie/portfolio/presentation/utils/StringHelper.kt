package itsme.ronjie.portfolio.presentation.utils

fun String.encodeURLParameter(): String {
    return this.replace(" ", "%20")
        .replace("\n", "%0A")
        .replace(":", "%3A")
        .replace("@", "%40")
}