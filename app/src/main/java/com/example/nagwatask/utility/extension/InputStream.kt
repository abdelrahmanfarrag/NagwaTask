package com.example.nagwatask.utility.extension

import java.io.IOException
import java.io.InputStream

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
fun InputStream.convertInputStreamToString():String?{
    return try {
      val bytes = ByteArray(this.available())
      this.read(bytes, 0, bytes.size)
      String(bytes)
    } catch (e: IOException) {
      null
    }

}
