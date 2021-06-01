package com.example.nagwatask.di.presentation.module

import androidx.lifecycle.ViewModel
import com.example.nagwatask.di.presentation.scopes.ViewModelKey
import com.example.nagwatask.di.presentation.viewmodel.ViewModelProviderModule
import com.example.nagwatask.presentation.main.MainActivityViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
@Module(includes = [ViewModelProviderModule::class])
abstract class ActivityViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(MainActivityViewModel::class)
  abstract fun bindsMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

}