package com.example.nagwatask.presentation.file.filefragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.nagwatask.domain.usecase.FilesUseCase
import com.example.nagwatask.presentation.file.filefragment.uimodel.FileUIModel
import com.example.nagwatask.presentation.file.filefragment.uimodel.toUIModel
import com.example.nagwatask.utility.Constants
import com.example.nagwatask.utility.DownloadFileWorkManager
import com.example.nagwatask.utility.extension.deserializeFromGson
import com.example.nagwatask.utility.extension.serializeToGson
import com.google.gson.Gson
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
class FilesViewModel @Inject constructor(
  private val filesUseCase: FilesUseCase,
  private val workManager: WorkManager,
  private val gson: Gson,
  private val constraints: Constraints,
  private val dataBuilder: Data.Builder
) : ViewModel() {

  private var compositeDisposable = CompositeDisposable()
  private var _filesList = MutableLiveData<List<FileUIModel>>()

  val filesList: LiveData<List<FileUIModel>>
    get() = _filesList

  var operationState: LiveData<WorkInfo>? = null

  fun loadFilesList() {
    compositeDisposable.add(
      filesUseCase.getFiles()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          _filesList.value = it.map { fileEntity ->
            fileEntity.toUIModel()
          }
        }, {
          it.printStackTrace()
        })
    )
  }

  fun postOperationUpdateToView(item: FileUIModel) {
    if (item.failedCount < 3) {
      val oneTimeWorkerBuilder =
        OneTimeWorkRequest.Builder(DownloadFileWorkManager::class.java).setConstraints(constraints)
      dataBuilder.putString(
        Constants.Data.SEND_DOWNLOAD_ITEM_TO_DOWNLOAD_FILE_MANAGER, item.serializeToGson(gson)
      )
      oneTimeWorkerBuilder.setInputData(dataBuilder.build())
      oneTimeWorkerBuilder.build()
      val workRequest = oneTimeWorkerBuilder.build()
      val id = workRequest.id
      workManager.enqueue(workRequest)
      operationState = workManager.getWorkInfoByIdLiveData(id)
    }
  }

  fun updateFilesList(
    itemString: String
  ) {
    val item = itemString.deserializeFromGson(gson)
    val items = filesList.value
    items?.let { uiModelList ->
      uiModelList.find { it.id == item.id }?.let {
        val newItem = it.copy(
          id = item.id,
          type = item.type,
          url = item.url,
          name = item.name,
          isDownloaded = item.isDownloaded,
          fileUri = item.fileUri,
          failedCount = item.failedCount
        )
        _filesList.value = uiModelList.map { uiItem ->
          if (uiItem.id == item.id)
            newItem
          else
            uiItem
        }
      }
    }
  }

  override fun onCleared() {
    compositeDisposable.dispose()
  }
}