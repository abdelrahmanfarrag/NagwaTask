package com.example.nagwatask.data.mapper

import com.example.nagwatask.data.model.FilesResponse
import com.example.nagwatask.domain.entity.FileEntity

/**
 * Authored by Abdelrahman Ahmed on 03 Jun, 2021.
 */
fun FilesResponse.mapToEntity() = FileEntity(
  id = id,
  type = type,
  url = url,
  name = name
)