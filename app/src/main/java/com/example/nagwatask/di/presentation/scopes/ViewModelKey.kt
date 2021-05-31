package com.example.nagwatask.di.presentation.scopes

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 * Contact: afarrag@youxel.com
 * by :YOUXEL
 */
@Target(
  AnnotationTarget.FUNCTION,
  AnnotationTarget.PROPERTY_GETTER,
  AnnotationTarget.PROPERTY_SETTER
)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)