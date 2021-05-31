package com.example.nagwatask.di.application

import android.app.Application
import com.example.nagwatask.di.application.scope.ApplicationScope
import com.example.nagwatask.di.presentation.ActivitySubComponent
import dagger.BindsInstance
import dagger.Component

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
@ApplicationScope
@Component(modules = [AppModule::class])
interface AppComponent {

  fun getActivitySubComponent(): ActivitySubComponent.Builder

  @Component.Builder
  interface Builder {

    @BindsInstance fun bindApplication(application: Application): Builder

    fun build(): AppComponent
  }
}