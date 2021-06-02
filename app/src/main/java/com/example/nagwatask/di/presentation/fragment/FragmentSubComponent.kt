package com.example.nagwatask.di.presentation.fragment

import android.content.Context
import com.example.nagwatask.di.presentation.fragment.module.FragmentModule
import com.example.nagwatask.di.presentation.fragment.module.FragmentViewModelModule
import com.example.nagwatask.di.presentation.scopes.PerFragment
import com.example.nagwatask.presentation.file.fragment.FilesFragment
import dagger.BindsInstance
import dagger.Subcomponent

/**
 * Authored by Abdelrahman Ahmed on 03 Jun, 2021.
 */
@PerFragment
@Subcomponent(modules = [FragmentViewModelModule::class, FragmentModule::class])
interface FragmentSubComponent {

  fun inject(view: FilesFragment)

  @Subcomponent.Builder
  interface Builder {

    @BindsInstance fun bindContext(application: Context): Builder

    fun build(): FragmentSubComponent
  }
}