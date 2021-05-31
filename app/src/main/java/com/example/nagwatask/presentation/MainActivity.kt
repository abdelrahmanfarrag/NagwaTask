package com.example.nagwatask.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.nagwatask.NagwaApplication
import com.example.nagwatask.databinding.ActivityMainBinding
import com.example.nagwatask.di.presentation.ActivitySubComponent
import com.example.nagwatask.presentation.adapter.FilesAdapter
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

  @Inject lateinit var mainActivityBinding: ActivityMainBinding
  @Inject lateinit var filesAdapter: FilesAdapter

  private val activitySubComponent: ActivitySubComponent by lazy {
    (application as NagwaApplication).appComponent.getActivitySubComponent().bindContext(this)
      .build()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activitySubComponent.inject(this)
    setContentView(mainActivityBinding.root)
  }

}
