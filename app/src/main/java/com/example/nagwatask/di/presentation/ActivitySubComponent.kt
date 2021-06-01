package com.example.nagwatask.di.presentation

import android.content.Context
import com.example.nagwatask.presentation.main.MainActivity
import com.example.nagwatask.di.presentation.module.ActivityModule
import com.example.nagwatask.di.presentation.module.ActivityViewModelModule
import com.example.nagwatask.di.presentation.scopes.PerActivity
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
@PerActivity
@Subcomponent(modules = [ActivityViewModelModule::class, ActivityModule::class])
interface ActivitySubComponent {

  fun inject(mainActivity: MainActivity)

  @Subcomponent.Builder
  interface Builder {

    @BindsInstance fun bindContext(application: Context): Builder

    fun build(): ActivitySubComponent
  }
}