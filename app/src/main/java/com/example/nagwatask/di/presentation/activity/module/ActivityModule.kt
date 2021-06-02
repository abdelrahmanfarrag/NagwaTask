package com.example.nagwatask.di.presentation.activity.module

import android.content.Context
import android.view.LayoutInflater
import com.example.nagwatask.data.repository.FilesRepositoryImpl
import com.example.nagwatask.databinding.ActivityMainBinding
import com.example.nagwatask.di.presentation.scopes.PerActivity
import com.example.nagwatask.domain.repository.FilesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
@Module
abstract class ActivityModule {

  companion object {
    @Provides
    @PerActivity
    fun providesLayoutInflater(context: Context): LayoutInflater {
      return LayoutInflater.from(context)
    }
    @Provides
    @PerActivity
    fun providesMainBinding(inflater: LayoutInflater): ActivityMainBinding {
      return ActivityMainBinding.inflate(inflater)
    }
  }
}