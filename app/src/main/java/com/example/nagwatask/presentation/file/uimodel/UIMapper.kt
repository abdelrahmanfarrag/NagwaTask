package com.example.nagwatask.presentation.file.uimodel

import com.example.nagwatask.domain.entity.FileEntity

/**
 * Authored by Abdelrahman Ahmed on 03 Jun, 2021.
 */

fun FileEntity.toUIModel() = FileUIModel(
  id = id,
  type = type,
  url = url,
  name = name,
  isDownloaded = isDownloaded,
  failedCount = failedCount,
  fileUri = fileUri
)