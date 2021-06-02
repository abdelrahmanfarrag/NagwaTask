package com.example.nagwatask.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.nagwatask.domain.model.FilesResponse
import com.example.nagwatask.data.repository.DownloadRepositoryImpl
import com.example.nagwatask.data.repository.FilesRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
class MainActivityViewModel @Inject constructor(
  private val filesRepositoryImpl: FilesRepositoryImpl,
  private val downloadRepositoryImpl: DownloadRepositoryImpl,
  private val workManager: WorkManager
) :
  ViewModel() {

  private var compositeDisposable = CompositeDisposable()
  private var _filesList = MutableLiveData<List<FilesResponse>>()

  val filesLit: LiveData<List<FilesResponse>>
    get() = _filesList

  var operationState: LiveData<WorkInfo>? = null

  fun loadFilesList() {
    compositeDisposable.add(
      filesRepositoryImpl.getFilesList()
        .flatMap {
          return@flatMap filesRepositoryImpl.getFilesList()
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          _filesList.value = it
        }, {})
    )
  }

  fun postOperationUpdateToView(item: FilesResponse) {
    if (downloadRepositoryImpl.downloadFile(item)!=null) {
      val workRequest = downloadRepositoryImpl.downloadFile(item)
      val id = workRequest?.id
      workManager.enqueue(workRequest!!)
      operationState = workManager.getWorkInfoByIdLiveData(id!!)
    }
  }

  override fun onCleared() {
    if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    super.onCleared()
  }
}