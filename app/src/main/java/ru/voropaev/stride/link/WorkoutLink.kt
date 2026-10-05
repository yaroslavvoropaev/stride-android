package ru.voropaev.stride.link

import android.net.Uri

object WorkoutLink {
    private const val SCHEME: String = "stride"
    private const val AUTHORITY = "workout"

    fun createLink(id: Long): Uri {
        return Uri.Builder()
            .scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(id.toString())
            .build()
    }

    fun parse(uri: Uri?): Long? {
        if (uri != null &&
            uri.scheme == SCHEME &&
            uri.authority == AUTHORITY &&
            uri.pathSegments.size == 1) {
            return uri.pathSegments.first().toLongOrNull()
        }
        return null
    }

}