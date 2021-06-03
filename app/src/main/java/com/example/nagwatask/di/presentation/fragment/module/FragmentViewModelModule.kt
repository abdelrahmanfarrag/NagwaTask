package com.example.nagwatask.di.presentation.fragment.module

import androidx.lifecycle.ViewModel
import com.example.nagwatask.di.presentation.scopes.ViewModelKey
import com.example.nagwatask.di.presentation.viewmodel.ViewModelProviderModule
import com.example.nagwatask.presentation.file.filefragment.FilesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Authored by Abdelrahman Ahmed on 03 Jun, 2021.
 */
@Module(includes = [ViewModelProviderModule::class])
abstract class FragmentViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(FilesViewModel::class)
  abstract fun bindsMainActivityViewModel(filesViewModel: FilesViewModel): ViewModel

}