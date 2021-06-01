package com.example.nagwatask.utility

import android.content.Context
import android.os.Environment
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.nagwatask.data.locale.response.FakeListResponse
import com.example.nagwatask.utility.Constants.Data.SEND_DOWNLOAD_ITEM_TO_DOWNLOAD_FILE_MANAGER
import com.example.nagwatask.utility.Constants.Data.SEND_RESULTED_ITEM_VIEW_TO_UPDATE
import com.example.nagwatask.utility.Constants.Error.EMPTY_FIELD_ERROR
import com.example.nagwatask.utility.Constants.FileType.MP4_EXTENSION
import com.example.nagwatask.utility.Constants.FileType.PDF
import com.example.nagwatask.utility.Constants.FileType.PDF_EXTENSION
import com.example.nagwatask.utility.extension.deserializeFromGson
import com.example.nagwatask.utility.extension.serializeToGson
import com.google.gson.Gson
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Exception
import java.net.URL

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
class DownloadFileWorkManager(
  context: Context,
  workParams: WorkerParameters
) :
  Worker(context, workParams) {

  private lateinit var item: FakeListResponse

  override fun doWork(): Result {
    val inData = this.inputData.getString(SEND_DOWNLOAD_ITEM_TO_DOWNLOAD_FILE_MANAGER) ?: ""
    val data = Data.Builder()
    return try {
      item = inData.deserializeFromGson(Gson())
      download(item.url ?: EMPTY_FIELD_ERROR)
      data.putString(SEND_RESULTED_ITEM_VIEW_TO_UPDATE, item.serializeToGson(Gson()))
      Result.success(data.build())
    } catch (e: Exception) {
      data.putString(SEND_RESULTED_ITEM_VIEW_TO_UPDATE, item.serializeToGson(Gson()))
      Result.failure(data.build())
    }
  }

  private fun download(link: String) {
    URL(link).openStream().use { input ->
      FileOutputStream(createImageFile()).use { output ->
        input.copyTo(output)
      }
    }
  }

  @Throws(IOException::class)
  private fun createImageFile(): File {
    val suffix = if (item.type == PDF) PDF_EXTENSION else MP4_EXTENSION
    val storageDir: File = applicationContext.getExternalFilesDir(Environment.DIRECTORY_DCIM)!!
    return File.createTempFile(
      item.name ?: EMPTY_FIELD_ERROR,
      suffix,
      storageDir
    ).apply {
      item.fileUri = this.absolutePath
      item.isDownloaded = true
    }
  }

}