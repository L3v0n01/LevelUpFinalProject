package com.adobe.finalProject

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.fragment.app.Fragment


/**
 * Created by Levon Arzumanyan on 10/26/21.
 * Project Name: ARMED
 * NOORLOGIC
 */
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

