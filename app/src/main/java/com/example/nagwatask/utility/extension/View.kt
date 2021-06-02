package com.example.nagwatask.utility.extension

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import com.example.nagwatask.R

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
fun View.visible() {
  visibility = View.VISIBLE
}

fun View.invisible() {
  visibility = View.INVISIBLE
}

fun View.gone() {
  visibility = View.GONE
}

fun ImageView.setResourceToImageView(@DrawableRes id: Int) {
  this.setImageDrawable(
    ContextCompat.getDrawable(
      this.context,
      id
    ))
}