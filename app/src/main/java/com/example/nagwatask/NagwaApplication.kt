package com.example.nagwatask

import android.app.Application
import com.example.nagwatask.di.application.AppComponent
import com.example.nagwatask.di.application.DaggerAppComponent

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
class NagwaApplication : Application() {

  lateinit var appComponent: AppComponent

  override fun onCreate() {
    super.onCreate()
    setupInjection()
  }

  private fun setupInjection() {
    appComponent = DaggerAppComponent.builder()
      .bindApplication(this)
      .build()
  }

}