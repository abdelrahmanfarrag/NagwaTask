package com.example.nagwatask.data.repository

import android.content.Context
import com.example.nagwatask.R
import com.example.nagwatask.data.locale.response.FakeListResponse
import com.example.nagwatask.utility.extension.convertInputStreamToString
import com.google.gson.Gson
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
class FilesRepositoryImpl @Inject constructor(
  private val context: Context,
  private val gson: Gson
) : FilesRepository {

  override fun getFilesList(): Observable<List<FakeListResponse>> {
    val filesJson =
      context.resources.openRawResource(R.raw.get_list_response).convertInputStreamToString()
    val filesList = gson.fromJson(filesJson, Array<FakeListResponse>::class.java).toList()
    return Observable.fromArray(filesList)
  }

}