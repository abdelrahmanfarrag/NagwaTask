package com.example.nagwatask.utility.extension

import com.example.nagwatask.data.locale.response.FakeListResponse
import com.google.gson.Gson

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
fun FakeListResponse.serializeToGson(gson: Gson): String = gson.toJson(this)

fun String.deserializeFromGson(gson: Gson): FakeListResponse =
  gson.fromJson(this, FakeListResponse::class.java)