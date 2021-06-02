package com.example.nagwatask.utility.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.Intent.ACTION_VIEW
import android.net.Uri
import android.widget.Toast
import com.example.nagwatask.R

/**
 * Authored by Abdelrahman Ahmed on 02 Jun, 2021.
 */
fun Activity.createChooserIntent(uri: Uri?) {
  Intent(ACTION_VIEW).apply {
    this.setDataAndType(uri, uri?.toString()?.getMimeType())
    this.addFlags(
      Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
    )
    if (this.resolveActivity(packageManager) != null) {
      val intentChooser = Intent.createChooser(this, "Choose App")
      this@createChooserIntent.startActivity(intentChooser)
    } else {
      Toast.makeText(
        this@createChooserIntent,
        getString(R.string.no_app_to_view),
        Toast.LENGTH_SHORT
      ).show()
    }
  }
}