package com.example.nagwatask.utility.extension

import com.example.nagwatask.presentation.file.uimodel.FileUIModel
import com.google.gson.Gson

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
fun<T> T.serializeToGson(gson: Gson): String = gson.toJson(this)

fun String.deserializeFromGson(gson: Gson): FileUIModel =
  gson.fromJson(this, FileUIModel::class.java)

