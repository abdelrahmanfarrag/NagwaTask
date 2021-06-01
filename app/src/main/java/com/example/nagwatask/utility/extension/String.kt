package com.example.nagwatask.utility.extension

import android.webkit.MimeTypeMap

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