package com.example.nagwatask.presentation.file.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.work.WorkInfo
import com.example.nagwatask.NagwaApplication
import com.example.nagwatask.databinding.FragmentFilesBinding
import com.example.nagwatask.di.presentation.fragment.FragmentSubComponent
import com.example.nagwatask.di.presentation.viewmodel.ViewModelFactoryProvider
import com.example.nagwatask.presentation.file.fragment.adapter.FilesAdapter
import com.example.nagwatask.utility.Constants
import com.example.nagwatask.utility.extension.createChooserIntent
import com.example.nagwatask.utility.extension.getFileUri
import com.example.nagwatask.utility.extension.gone
import com.example.nagwatask.utility.extension.visible
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 03 Jun, 2021.
 */
class FilesFragment : Fragment() {

  @Inject lateinit var fragmentFilesBinding: FragmentFilesBinding
  @Inject lateinit var factoryProvider: ViewModelFactoryProvider
  @Inject lateinit var filesAdapter: FilesAdapter

  private val filesViewModel by lazy {
    ViewModelProvider(this, factoryProvider).get(FilesViewModel::class.java)
  }

  private val fragmentSubComponent: FragmentSubComponent by lazy {
    ((requireActivity().applicationContext) as NagwaApplication).appComponent.getFragmentSubComponent()
      .bindContext(requireContext())
      .build()
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    fragmentSubComponent.inject(this)
    super.onCreate(savedInstanceState)

  }
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return fragmentFilesBinding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    filesViewModel.loadFilesList()
    updateUI()

  }

  private fun observeWorkManager() {
    filesViewModel.operationState?.let { workInfo ->
      workInfo.observe(viewLifecycleOwner, Observer { info ->
        val outputData =
          info.outputData.getString(Constants.Data.SEND_RESULTED_ITEM_VIEW_TO_UPDATE)
            ?: ""
        when {
          info.state == WorkInfo.State.RUNNING -> {
            fragmentFilesBinding.loadingIndicatorView.visible()
          }
          info.state == WorkInfo.State.FAILED -> {
            fragmentFilesBinding.loadingIndicatorView.gone()
            filesViewModel.updateFilesList(outputData)
          }
          info.state.isFinished -> {
            fragmentFilesBinding.loadingIndicatorView.gone()
            filesViewModel.updateFilesList(outputData)
          }
        }
      })
    }
  }

  private fun updateUI() {
    filesViewModel.filesList.observe(viewLifecycleOwner, Observer { filesList ->
      filesAdapter.setOnClicked { item ->
        filesViewModel.postOperationUpdateToView(item)
        observeWorkManager()
      }
      filesAdapter.setOnViewVideoClicked { uri ->
        requireActivity().createChooserIntent(uri?.getFileUri(requireContext()))
      }
      fragmentFilesBinding.fileRV.adapter = filesAdapter
      filesAdapter.setItems(filesList)
    })
  }

}