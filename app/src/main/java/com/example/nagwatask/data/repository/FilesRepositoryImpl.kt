package com.example.nagwatask.data.repository

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.nagwatask.R
import com.example.nagwatask.data.locale.response.FakeListResponse
import com.example.nagwatask.utility.Constants.Data.SEND_DOWNLOAD_ITEM_TO_DOWNLOAD_FILE_MANAGER
import com.example.nagwatask.utility.DownloadFileWorkManager
import com.example.nagwatask.utility.extension.convertInputStreamToString
import com.example.nagwatask.utility.extension.serializeToGson
import com.google.gson.Gson
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
class FilesRepositoryImpl @Inject constructor(
  private val context: Context,
  private val gson: Gson,
  private val workManager: WorkManager,
  private val constraints: Constraints,
  private val dataBuilder: Data.Builder
) : FilesRepository {

  override fun getFilesList(): Observable<List<FakeListResponse>> {
    val filesJson =
      context.resources.openRawResource(R.raw.get_list_response).convertInputStreamToString()
    val filesList = gson.fromJson(filesJson, Array<FakeListResponse>::class.java).toList()
    return Observable.fromArray(filesList)
  }

  override fun downloadFile(item: FakeListResponse,tag:String) {
    val oneTimeWorkerBuilder =
      OneTimeWorkRequest.Builder(DownloadFileWorkManager::class.java).setConstraints(constraints)
    dataBuilder.putString(SEND_DOWNLOAD_ITEM_TO_DOWNLOAD_FILE_MANAGER, item.serializeToGson(gson))
    oneTimeWorkerBuilder.setInputData(dataBuilder.build())
    workManager.enqueue(oneTimeWorkerBuilder.addTag(tag).build())
  }
}