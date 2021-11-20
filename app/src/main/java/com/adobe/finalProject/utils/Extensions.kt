package com.adobe.finalProject

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment

fun Fragment.goToFacebookPageByUrl(pm: PackageManager, fbPageUrl: String) {
    var uri: Uri = Uri.parse(fbPageUrl)
    try {
        val applicationInfo = pm.getApplicationInfo("com.facebook.katana", 0)
        if (applicationInfo.enabled) {
            uri = Uri.parse("fb://facewebmodal/f?href=$fbPageUrl")
        }
    } catch (ignored: PackageManager.NameNotFoundException) {
    }
    startActivity(Intent(Intent.ACTION_VIEW, uri))
}

fun Fragment.goToAnotherAppByUrl(channelUrl: String) =
    try {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(channelUrl)))
    } catch (e: Exception) {
    }

fun runDelayed(delay: Long, delayedFunction: () -> Unit) {
    Handler(Looper.getMainLooper()).postDelayed({
        delayedFunction.invoke()
    }, delay)
}

