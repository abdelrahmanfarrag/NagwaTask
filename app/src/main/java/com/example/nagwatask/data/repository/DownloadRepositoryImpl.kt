package com.example.nagwatask.data.repository

import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkRequest
import com.example.nagwatask.domain.model.FilesResponse
import com.example.nagwatask.domain.repository.DownloadRepository
import com.example.nagwatask.utility.Constants.Data.SEND_DOWNLOAD_ITEM_TO_DOWNLOAD_FILE_MANAGER
import com.example.nagwatask.utility.DownloadFileWorkManager
import com.example.nagwatask.utility.extension.serializeToGson
import com.google.gson.Gson
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 02 Jun, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
class DownloadRepositoryImpl @Inject constructor(
  private val constraints: Constraints,
  private val dataBuilder: Data.Builder,
  private val gson: Gson
) : DownloadRepository {

  override fun downloadFile(item: FilesResponse): WorkRequest? {
    return if (item.failedCount < 3) {
      val oneTimeWorkerBuilder =
        OneTimeWorkRequest.Builder(DownloadFileWorkManager::class.java).setConstraints(constraints)
      dataBuilder.putString(
        SEND_DOWNLOAD_ITEM_TO_DOWNLOAD_FILE_MANAGER,
        item.serializeToGson(gson)
      )
      oneTimeWorkerBuilder.setInputData(dataBuilder.build())
      oneTimeWorkerBuilder.build()
    } else
      null
  }

}