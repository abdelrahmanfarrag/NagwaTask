package com.example.nagwatask.presentation.main

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.Observer
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModelProvider
import androidx.work.Operation
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.example.nagwatask.BuildConfig
import com.example.nagwatask.NagwaApplication
import com.example.nagwatask.data.locale.response.FakeListResponse
import com.example.nagwatask.databinding.ActivityMainBinding
import com.example.nagwatask.di.presentation.ActivitySubComponent
import com.example.nagwatask.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.nagwatask.presentation.adapter.FilesAdapter
import com.example.nagwatask.utility.extension.deserializeFromGson
import com.example.nagwatask.utility.extension.getMimeType
import com.google.gson.Gson
import java.io.File
import java.util.Objects
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

  @Inject lateinit var mainActivityBinding: ActivityMainBinding
  @Inject lateinit var filesAdapter: FilesAdapter
  @Inject lateinit var factoryProvider: ViewModelFactoryProvider
  @Inject lateinit var gson: Gson
  @Inject lateinit var workManager: WorkManager

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
    observeDownloadStatus()
  }

  private fun observeDownloadStatus() {
  }

  private fun setupWorkManager(item: FakeListResponse) {
//    workManager = WorkManager.getInstance(this)
//    val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTEDCONNECTED).build()
//    task =
//      OneTimeWorkRequest.Builder(DownloadFileWorkManager::class.java).setConstraints(constraints)
//    val dataBuilder = Data.Builder()
//    dataBuilder.putString("item", item.serializeToGson(gson))
//    task.setInputData(dataBuilder.build())
//    val taskBuilder = task.build()
//    workManager.enqueue(taskBuilder)
//    workManager.getWorkInfoByIdLiveData(taskBuilder.id)
//      .observe(this, Observer {
//        it?.let { workInfo ->
//          Log.d(
//            "printProgress", workInfo.progress.toString()
//          )
//          when {
//            workInfo.state == WorkInfo.State.RUNNING -> {
//            }
//            workInfo.state == WorkInfo.State.FAILED -> {
//            }
//            workInfo.state.isFinished -> {
//              val outputData = workInfo.outputData.getString("item") ?: ""
//              val updateFakeObject = outputData.deserializeFromGson(gson)
//              if (outputData.isNotEmpty()) {
//                val videoFile = File(updateFakeObject.fileUri ?: "")
//                val file: Uri = FileProvider.getUriForFile(
//                  Objects.requireNonNull(applicationContext),
//                  BuildConfig.APPLICATION_ID + ".provider", videoFile
//                )
//                val intent = Intent(Intent.ACTION_VIEW)
//                intent.setDataAndType(file, updateFakeObject.type?.getMimeType())
//                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION) //DO NOT FORGET THIS EVER
//                startActivity(intent)
//              } else {
//              }
//            }
//            else -> {
//            }
//          }
//        }
//      })
  }

  private fun updateUI() {

    mainViewModel.filesLit.observe(this, Observer { filesList ->
      filesAdapter.setItems(filesList)
      filesAdapter.setOnClicked { item, pos ->
      }
      mainActivityBinding.fileRV.adapter = filesAdapter
    })
  }
}
