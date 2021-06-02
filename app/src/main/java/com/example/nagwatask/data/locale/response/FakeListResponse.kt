package com.example.nagwatask.data.locale.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
data class FakeListResponse(
  @field:SerializedName("id") val id: Int? = null,
  @field:SerializedName("type") val type: String? = null,
  @field:SerializedName("url") val url: String? = null,
  @field:SerializedName("name") var name: String? = null,
  @Expose var isDownloaded: Boolean = false,
  @Expose var fileUri: String = "",
  @Expose var failedCount: Int = 0
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
    if (isDownloaded != other.isDownloaded) return false
    if (fileUri != other.fileUri) return false
    if (failedCount != other.failedCount) return false
    return true
  }

  override fun hashCode(): Int {
    var result = id ?: 0
    result = 31 * result + (type?.hashCode() ?: 0)
    result = 31 * result + (url?.hashCode() ?: 0)
    result = 31 * result + (name?.hashCode() ?: 0)
    result = 31 * result + (isDownloaded.hashCode())
    result = 31 * result + (fileUri.hashCode())
    result = 31 * result + (failedCount.hashCode())
    return result
  }
}