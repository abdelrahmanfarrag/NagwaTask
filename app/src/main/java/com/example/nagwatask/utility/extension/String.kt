package com.example.nagwatask.utility.extension

import android.content.Context
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.core.content.FileProvider
import com.example.nagwatask.BuildConfig
import java.io.File
import java.util.Objects

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
fun String.getMimeType(): String? {
  var type: String? = null
  val extension: String? = MimeTypeMap.getFileExtensionFromUrl(this)
  if (extension != null) {
    type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
  }
  return type
}

fun String.getFileUri(context: Context): Uri? {
  val file = File(this)
  return FileProvider.getUriForFile(
    Objects.requireNonNull(context),
    BuildConfig.APPLICATION_ID + ".provider", file
  )
}

fun String.startDownloadAFileFromALink() {

}