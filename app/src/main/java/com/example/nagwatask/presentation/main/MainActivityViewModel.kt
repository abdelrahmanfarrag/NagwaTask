package com.example.nagwatask.presentation.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nagwatask.data.locale.response.FakeListResponse
import com.example.nagwatask.data.repository.FilesRepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.UUID
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
class MainActivityViewModel @Inject constructor(
  private val filesRepositoryImpl: FilesRepositoryImpl,
  private val application: Application
) :
  ViewModel() {

  private var compositeDisposable = CompositeDisposable()
  private var _filesList = MutableLiveData<List<FakeListResponse>>()
  private var _operationState = MutableLiveData<UUID>()

  val filesLit: LiveData<List<FakeListResponse>>
    get() = _filesList


  fun loadFilesList() {
    compositeDisposable.add(
      filesRepositoryImpl.getFilesList()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({
          _filesList.value = it
        }, {})
    )
  }


  override fun onCleared() {
    if (!compositeDisposable.isDisposed) compositeDisposable.dispose()
    super.onCleared()
  }
}