package com.example.nagwatask.di.presentation.activity

import android.content.Context
import com.example.nagwatask.presentation.file.FileActivity
import com.example.nagwatask.di.presentation.activity.module.ActivityModule
import com.example.nagwatask.di.presentation.activity.module.ActivityViewModelModule
import com.example.nagwatask.di.presentation.scopes.PerActivity
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
@PerActivity
@Subcomponent(modules = [ActivityViewModelModule::class, ActivityModule::class])
interface ActivitySubComponent {

  fun inject(fileActivity: FileActivity)

  @Subcomponent.Builder
  interface Builder {

    @BindsInstance fun bindContext(application: Context): Builder

    fun build(): ActivitySubComponent
  }
}