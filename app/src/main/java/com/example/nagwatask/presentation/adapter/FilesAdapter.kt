package com.example.nagwatask.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nagwatask.data.locale.response.FakeListResponse
import com.example.nagwatask.databinding.ItemFileBinding
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
@Suppress("ReplaceCallWithBinaryOperator")
class FilesAdapter @Inject constructor() : RecyclerView.Adapter<FilesViewHolder>() {

  private lateinit var onDownloadClicked: (FakeListResponse) -> Unit
  private lateinit var onViewVideoClicked: (String?) -> Unit

  private val diffCallback = object : DiffUtil.ItemCallback<FakeListResponse>() {
    override fun areItemsTheSame(oldItem: FakeListResponse, newItem: FakeListResponse): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FakeListResponse, newItem: FakeListResponse): Boolean {
      return oldItem.equals(newItem)
    }
  }
  val differ = AsyncListDiffer(this, diffCallback)
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesViewHolder {
    val binding = ItemFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return FilesViewHolder(binding, onDownloadClicked, onViewVideoClicked)
  }

  fun setOnClicked(onDownloadClicked: (FakeListResponse) -> Unit) {
    this.onDownloadClicked = onDownloadClicked
  }

  fun setOnViewVideoClicked(onViewVideoClick: (String?) -> Unit) {
    this.onViewVideoClicked = onViewVideoClick
  }

  override fun getItemCount() = differ.currentList.size

  override fun onBindViewHolder(holder: FilesViewHolder, position: Int) {
    holder.bind(differ.currentList[position])
  }
}