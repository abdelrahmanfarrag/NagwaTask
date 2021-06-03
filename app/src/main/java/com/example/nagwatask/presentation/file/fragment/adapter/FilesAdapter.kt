package com.example.nagwatask.presentation.file.fragment.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nagwatask.domain.model.FilesResponse
import com.example.nagwatask.databinding.ItemFileBinding
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
class FilesAdapter @Inject constructor() :
  RecyclerView.Adapter<FilesViewHolder>() {

  private lateinit var onDownloadClicked: (FilesResponse) -> Unit
  private lateinit var onViewVideoClicked: (String?) -> Unit
  private val diffCallback = object : DiffUtil.ItemCallback<FilesResponse>() {
    override fun areItemsTheSame(oldItem: FilesResponse, newItem: FilesResponse): Boolean {
      return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: FilesResponse, newItem: FilesResponse): Boolean {
      return oldItem == newItem
    }
  }
   val differ = AsyncListDiffer(this, diffCallback)

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesViewHolder {
    val binding = ItemFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return FilesViewHolder(binding, onDownloadClicked, onViewVideoClicked)
  }

  override fun getItemCount() = differ.currentList.size

  override fun onBindViewHolder(holder: FilesViewHolder, position: Int) {
    holder.bind(differ.currentList[position])
  }

  fun setOnClicked(onDownloadClicked: (FilesResponse) -> Unit) {
    this.onDownloadClicked = onDownloadClicked
  }

  fun setOnViewVideoClicked(onViewVideoClick: (String?) -> Unit) {
    this.onViewVideoClicked = onViewVideoClick
  }


  fun setItems(filesList: List<FilesResponse?>) {
    differ.submitList(filesList)
  }
}