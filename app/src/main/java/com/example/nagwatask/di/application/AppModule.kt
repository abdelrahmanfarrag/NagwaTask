package com.example.nagwatask.di.application

import android.app.Application
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.WorkManager
import com.example.nagwatask.di.application.scope.ApplicationScope
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
@Module
class AppModule {
  @Provides
  @ApplicationScope fun providesGson() = GsonBuilder().setLenient().create()

  @Provides
  @ApplicationScope fun provideWorkManagerInstance(context: Application) =
    WorkManager.getInstance(context)

  @Provides
  @ApplicationScope fun providesConstraintToWorkManager(): Constraints {
    return Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
  }

  @Provides
  @ApplicationScope fun providesDataBuilder() = Data.Builder()

}