package com.example.nagwatask.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nagwatask.data.locale.response.FakeListResponse
import com.example.nagwatask.databinding.ItemFileBinding
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
class FilesAdapter @Inject constructor() : RecyclerView.Adapter<FilesViewHolder>() {

  private var items = mutableListOf<FakeListResponse>()

  private lateinit var onDownloadClicked: (View, String) -> Unit

  fun setItems(items: List<FakeListResponse>) {
    this.items = items.toMutableList()
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesViewHolder {
    val binding = ItemFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return FilesViewHolder(binding, onDownloadClicked)
  }

  fun setOnClicked(onDownloadClicked: (View, String) -> Unit) {
    this.onDownloadClicked = onDownloadClicked
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: FilesViewHolder, position: Int) {
    holder.bind(items[position])
  }

}