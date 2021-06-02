package com.example.nagwatask.di.presentation.fragment.module

import android.content.Context
import android.view.LayoutInflater
import com.example.nagwatask.data.repository.FilesRepositoryImpl
import com.example.nagwatask.databinding.FragmentFilesBinding
import com.example.nagwatask.di.presentation.scopes.PerFragment
import com.example.nagwatask.domain.repository.FilesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

/**
 * Authored by Abdelrahman Ahmed on 03 Jun, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
@Module
abstract class FragmentModule {
  companion object {
    @Provides
    @PerFragment
    fun providesLayoutInflater(context: Context): LayoutInflater {
      return LayoutInflater.from(context)
    }

    @Provides
    @PerFragment
    fun providesMainBinding(inflater: LayoutInflater): FragmentFilesBinding {
      return FragmentFilesBinding.inflate(inflater)
    }
  }

  @Binds
  @PerFragment
  abstract fun bindsFilesRepositoryImpl(filesRepositoryImpl: FilesRepositoryImpl): FilesRepository

}