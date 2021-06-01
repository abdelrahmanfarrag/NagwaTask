package com.example.nagwatask.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

  private var items = mutableListOf<FakeListResponse>()

  private lateinit var onDownloadClicked: (View, String, Int) -> Unit

  fun setItems(enteredItems: List<FakeListResponse>) {
    val oldList = items
    val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(
      FilesDiffCallback(oldList, enteredItems)
    )
    items = enteredItems.toMutableList()
    diffResult.dispatchUpdatesTo(this)
  }

  fun getItemAtPosition(position: Int) = items[position]

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilesViewHolder {
    val binding = ItemFileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return FilesViewHolder(binding, onDownloadClicked)
  }

  fun setOnClicked(onDownloadClicked: (View, String, Int) -> Unit) {
    this.onDownloadClicked = onDownloadClicked
  }

  override fun getItemCount() = items.size

  override fun onBindViewHolder(holder: FilesViewHolder, position: Int) {
    holder.bind(items[position])
  }

  class FilesDiffCallback(
    var oldFilesList: List<FakeListResponse>,
    var newFilesList: List<FakeListResponse>
  ) : DiffUtil.Callback() {
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return oldFilesList[oldItemPosition].id == newFilesList[newItemPosition].id
    }

    override fun getOldListSize() = oldFilesList.size

    override fun getNewListSize() = newFilesList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
      return oldFilesList[oldItemPosition].equals(newFilesList[newItemPosition])
    }
  }
}