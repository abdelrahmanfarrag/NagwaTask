package com.example.nagwatask.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nagwatask.R
import com.example.nagwatask.data.locale.response.FakeListResponse
import com.example.nagwatask.databinding.ItemFileBinding
import com.example.nagwatask.utility.Constants
import com.example.nagwatask.utility.extension.setResourceToImageView

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */
class FilesViewHolder(
  private val view: ItemFileBinding,
  private var onDownloadClicked: (View, String, Int) -> Unit
) : RecyclerView.ViewHolder(view.root) {

  fun bind(item: FakeListResponse) {
    view.fileDownloadImageView.setOnClickListener {
      onDownloadClicked.invoke(
        view.fileDownloadIndicatorTextView,
        item.name ?: "null",
        layoutPosition
      )
    }

    view.fileTitleTextView.text = item.name ?: "ERROR"
    when (item.type) {
      Constants.FileType.PDF -> view.fileTypeImageView.setResourceToImageView(R.drawable.ic_pdf)
      Constants.FileType.VIDEO -> view.fileTypeImageView.setResourceToImageView(R.drawable.ic_video)
    }
  }
}