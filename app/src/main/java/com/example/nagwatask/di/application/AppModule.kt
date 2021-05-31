package com.example.nagwatask.di.application

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
}