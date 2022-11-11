package com.silverbullet.devsworld.core.util

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns

fun Uri.fileName(context: Context): String? {
    if (scheme != "content") {
        return null
    }
    val cursor = context.contentResolver.query(
        this,
        null,
        null,
        null,
        null
    )
    val filename = cursor?.let { cur ->
        val nameIndex = cur.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        cur.moveToFirst()
        cur.getString(nameIndex)
    }
    cursor?.close()
    return filename
}