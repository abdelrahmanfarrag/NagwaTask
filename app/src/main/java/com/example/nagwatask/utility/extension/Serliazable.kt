package com.example.nagwatask.utility.extension

import com.example.nagwatask.domain.model.FilesResponse
import com.google.gson.Gson

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
fun FilesResponse.serializeToGson(gson: Gson): String = gson.toJson(this)

fun String.deserializeFromGson(gson: Gson): FilesResponse =
  gson.fromJson(this, FilesResponse::class.java)