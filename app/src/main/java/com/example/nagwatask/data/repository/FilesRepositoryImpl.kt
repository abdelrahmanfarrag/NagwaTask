package com.example.nagwatask.data.repository

import android.content.Context
import com.example.nagwatask.R
import com.example.nagwatask.data.mapper.mapToEntity
import com.example.nagwatask.data.model.FilesResponse
import com.example.nagwatask.domain.entity.FileEntity
import com.example.nagwatask.domain.repository.FilesRepository
import com.example.nagwatask.utility.extension.convertInputStreamToString
import com.google.gson.Gson
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
class FilesRepositoryImpl @Inject constructor(
  private val context: Context,
  private val gson: Gson
) : FilesRepository {

  override fun getFilesList(): Single<List<FileEntity>> {
    val filesJson =
      context.resources.openRawResource(R.raw.get_list_response).convertInputStreamToString()
    val filesList = gson.fromJson(filesJson, Array<FilesResponse>::class.java).toList()
    return Single.just(filesList.map { it.mapToEntity() })
  }

}