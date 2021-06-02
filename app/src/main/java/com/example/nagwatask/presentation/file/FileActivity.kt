package com.example.nagwatask.presentation.file

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.nagwatask.NagwaApplication
import com.example.nagwatask.R
import com.example.nagwatask.databinding.ActivityMainBinding
import com.example.nagwatask.di.presentation.activity.ActivitySubComponent
import javax.inject.Inject


class FileActivity : AppCompatActivity() {

  @Inject lateinit var mainActivityBinding: ActivityMainBinding
  private lateinit var navController: NavController

  private val activitySubComponent: ActivitySubComponent by lazy {
    (application as NagwaApplication).appComponent.getActivitySubComponent().bindContext(this)
      .build()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    activitySubComponent.inject(this)
    setContentView(mainActivityBinding.root)
    setupNavigation()
  }

  private fun setupNavigation(){
    val navHostFragment =
      supportFragmentManager.findFragmentById(R.id.hostFragmentContainerView) as NavHostFragment
    navController = navHostFragment.findNavController()

  }

  override fun onSupportNavigateUp(): Boolean {
    return navController.navigateUp() || super.onSupportNavigateUp()
  }
}
