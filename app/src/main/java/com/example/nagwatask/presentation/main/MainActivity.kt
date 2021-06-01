package com.example.nagwatask.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nagwatask.NagwaApplication
import com.example.nagwatask.databinding.ActivityMainBinding
import com.example.nagwatask.di.presentation.ActivitySubComponent
import com.example.nagwatask.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.nagwatask.presentation.adapter.FilesAdapter
import com.example.nagwatask.utility.DownloadFile
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

  private fun updateUI() {
    mainViewModel.filesLit.observe(this, Observer { filesList ->
      filesAdapter.setItems(filesList)
      filesAdapter.setOnClicked { item, pos ->
        DownloadFile.startDownloadFile(item, this) { _, isSuccess ->
          if (!isSuccess)
            item.failedCount++
          filesAdapter.notifyItemChanged(pos)
        }
      }
      mainActivityBinding.fileRV.adapter = filesAdapter
    })
  }
}
