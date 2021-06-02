package com.example.nagwatask.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.nagwatask.NagwaApplication
import com.example.nagwatask.data.locale.response.FakeListResponse
import com.example.nagwatask.databinding.ActivityMainBinding
import com.example.nagwatask.di.presentation.ActivitySubComponent
import com.example.nagwatask.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.nagwatask.presentation.adapter.FilesAdapter
import com.example.nagwatask.utility.Constants
import com.example.nagwatask.utility.extension.createChooserIntent
import com.example.nagwatask.utility.extension.deserializeFromGson
import com.example.nagwatask.utility.extension.getFileUri
import com.google.gson.Gson
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

  @Inject lateinit var mainActivityBinding: ActivityMainBinding
  @Inject lateinit var filesAdapter: FilesAdapter
  @Inject lateinit var factoryProvider: ViewModelFactoryProvider
  @Inject lateinit var gson: Gson
  @Inject lateinit var workManager: WorkManager
  private var downloadedFilesList = mutableListOf<FakeListResponse>()

  private val mainViewModel by lazy {
    ViewModelProvider(this, factoryProvider).get(MainActivityViewModel::class.java)
  }

  private val activitySubComponent: ActivitySubComponent by lazy {
    (application as NagwaApplication).appComponent.getActivitySubComponent().bindContext(this)
      .build()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activitySubComponent.inject(this)
    mainViewModel.loadFilesList()
    setContentView(mainActivityBinding.root)
    updateUI()
  }

  private fun observeWorkManager(item: FakeListResponse) {
    mainViewModel.operationState?.let { workInfo ->
      workInfo.observe(this, Observer { info ->
        when {
          info.state == WorkInfo.State.RUNNING -> {
          }
          info.state == WorkInfo.State.FAILED -> {
            filesAdapter.differ.submitList(downloadedFilesList)
          }
          info.state.isFinished -> {
            val outputData =
              info.outputData.getString(Constants.Data.SEND_RESULTED_ITEM_VIEW_TO_UPDATE)
                ?: ""
//            val updateFakeObject = outputData.deserializeFromGson(gson)
//            downloadedFilesList.find { it.id == item.id }?.apply {
//              this.fileUri = updateFakeObject.fileUri
//              this.isDownloaded = updateFakeObject.isDownloaded
//            }
            filesAdapter.differ.submitList(downloadedFilesList)
          }
          else -> {
          }
        }
      })
    }
  }


  private fun updateUI() {
    mainViewModel.filesLit.observe(this, Observer { filesList ->
      downloadedFilesList.addAll(filesList)
      filesAdapter.differ.submitList(downloadedFilesList)
      filesAdapter.setOnClicked { item ->
        mainViewModel.postOperationUpdateToView(item)
        observeWorkManager(item)
      }
      filesAdapter.setOnViewVideoClicked { uri ->
        createChooserIntent(uri?.getFileUri(this))
      }
      mainActivityBinding.fileRV.adapter = filesAdapter
    })
  }
}
