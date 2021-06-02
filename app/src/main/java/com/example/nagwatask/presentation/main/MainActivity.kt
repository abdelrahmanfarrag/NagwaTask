package com.example.nagwatask.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkInfo
import com.example.nagwatask.NagwaApplication
import com.example.nagwatask.databinding.ActivityMainBinding
import com.example.nagwatask.di.presentation.ActivitySubComponent
import com.example.nagwatask.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.nagwatask.presentation.adapter.FilesAdapter
import com.example.nagwatask.utility.Constants
import com.example.nagwatask.utility.extension.createChooserIntent
import com.example.nagwatask.utility.extension.getFileUri
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

  @Inject lateinit var mainActivityBinding: ActivityMainBinding
  @Inject lateinit var filesAdapter: FilesAdapter
  @Inject lateinit var factoryProvider: ViewModelFactoryProvider

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

  private fun observeWorkManager() {
    mainViewModel.operationState?.let { workInfo ->
      workInfo.observe(this, Observer { info ->
        when {
          info.state == WorkInfo.State.RUNNING -> {
          }
          info.state == WorkInfo.State.FAILED -> {
            val outputData =
              info.outputData.getString(Constants.Data.SEND_RESULTED_ITEM_VIEW_TO_UPDATE)
                ?: ""
            filesAdapter.searchAndUpdateItem(outputData)
          }
          info.state.isFinished -> {
            val outputData =
              info.outputData.getString(Constants.Data.SEND_RESULTED_ITEM_VIEW_TO_UPDATE)
                ?: ""
            filesAdapter.searchAndUpdateItem(outputData)
          }
          else -> {
          }
        }
      })
    }
  }

  private fun updateUI() {
    mainViewModel.filesLit.observe(this, Observer { filesList ->
      filesAdapter.setOnClicked { item ->
          mainViewModel.postOperationUpdateToView(item)
        observeWorkManager()
      }
      filesAdapter.setOnViewVideoClicked { uri ->
        createChooserIntent(uri?.getFileUri(this))
      }
      mainActivityBinding.fileRV.adapter = filesAdapter
      filesAdapter.setItems(filesList)
    })
  }
}
