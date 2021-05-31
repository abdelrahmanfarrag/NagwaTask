package com.example.nagwatask.di.presentation.module

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.nagwatask.databinding.ActivityMainBinding
import com.example.nagwatask.di.presentation.scopes.PerActivity
import com.example.nagwatask.presentation.adapter.FilesAdapter
import dagger.Module
import dagger.Provides

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
@Module
class ActivityModule {

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