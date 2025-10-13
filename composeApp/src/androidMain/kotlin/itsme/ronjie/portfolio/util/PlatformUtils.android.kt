package itsme.ronjie.portfolio.util

import android.content.Intent
import android.net.Uri
import itsme.ronjie.portfolio.MainActivity

actual fun openUrl(url: String) {
    val context = MainActivity.instance
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context?.startActivity(intent)
}