package com.example.nagwatask.data.locale.response

import com.google.gson.annotations.SerializedName

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
data class FakeListResponse(
  @field:SerializedName("id") val id: Int? = null,
  @field:SerializedName("type") val type: String? = null,
  @field:SerializedName("url") val url: String? = null,
  @field:SerializedName("name") var name: String? = null
) {
  override fun equals(other: Any?): Boolean {
    if (javaClass != other?.javaClass) {
      return false
    }
    other as FakeListResponse
    if (id != other.id) return false
    if (type != other.type) return false
    if (url != other.url) return false
    if (name != other.name) return false
    return true
  }

  override fun hashCode(): Int {
    var result = id ?: 0
    result = 31 * result + (type?.hashCode() ?: 0)
    result = 31 * result + (url?.hashCode() ?: 0)
    result = 31 * result + (name?.hashCode() ?: 0)
    return result
  }
}