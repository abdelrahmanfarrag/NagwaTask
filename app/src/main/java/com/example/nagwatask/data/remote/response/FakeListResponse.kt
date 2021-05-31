package com.example.nagwatask.data.remote.response

import com.google.gson.annotations.SerializedName

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
data class FakeListResponse(
  @field:SerializedName("id") val id: Int? = null,
  @field:SerializedName("type") val type: String? = null,
  @field:SerializedName("url") val url: String? = null,
  @field:SerializedName("name") val name: String? = null
)