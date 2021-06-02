package com.example.nagwatask.utility

import android.annotation.SuppressLint
import android.app.DownloadManager
import android.app.DownloadManager.Query
import android.app.DownloadManager.Request
import android.content.Context
import android.net.Uri
import android.os.Environment
import com.example.nagwatask.domain.model.FilesResponse
import com.example.nagwatask.utility.extension.getMimeType
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.Locale

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */

@SuppressLint("CheckResult")
object DownloadFile {
 fun startDownloadFile(
   filesResponse: FilesResponse,
   context: Context,
   afterDownloadCompletedOrFailed: (String?, Boolean) -> Unit
  ) {
    var manager: DownloadManager? = null
    Completable.fromCallable {
      val request = Request(Uri.parse(filesResponse.url))
      request.apply {
        setDescription(
          "Download .. ${filesResponse.name}.${filesResponse.type?.toLowerCase(
            Locale.ENGLISH
          )}"
        )
        setTitle(
          "${filesResponse.name}.${filesResponse.type?.toLowerCase(
            Locale.ENGLISH
          )}"
        )
        setMimeType(filesResponse.url?.getMimeType())
        setNotificationVisibility(Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        setDestinationInExternalPublicDir(
          Environment.DIRECTORY_DOWNLOADS,
          "/${filesResponse.name}.${filesResponse.type?.toLowerCase(Locale.ENGLISH)}"
        )
        setAllowedOverMetered(true)
        setAllowedOverRoaming(true)
      }
      manager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
      manager?.enqueue(request)
    }.subscribeOn(Schedulers.io())
      .doOnSubscribe {}
      .observeOn(AndroidSchedulers.mainThread())
      .doFinally {}
      .subscribe({
        val quest = Query()
        val cursor = manager?.query(quest)
        cursor?.moveToFirst()
        if (cursor?.moveToNext() == true) {
          val downloadedBytes = cursor.getInt(
            cursor
              .getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
          )
          val downloadTotalBytes =
            cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES))
            if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)) == DownloadManager.STATUS_SUCCESSFUL) {
              val downloadedUri =
                cursor.getString(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_LOCAL_URI))
              afterDownloadCompletedOrFailed(downloadedUri, true)
            }
          cursor.close()
        }
      }, {
        afterDownloadCompletedOrFailed(null, false)

      })
  }
}



