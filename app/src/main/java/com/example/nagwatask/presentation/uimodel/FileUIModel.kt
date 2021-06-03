package com.example.nagwatask.presentation.uimodel

/**
 * Authored by Abdelrahman Ahmed on 03 Jun, 2021.
 */
data class FileUIModel(
  val id: Int? = null,
  val type: String? = null,
  val url: String? = null,
  var name: String? = null,
  var isDownloaded: Boolean = false,
  var fileUri: String = "",
  var failedCount: Int = 0
)