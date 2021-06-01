package com.example.nagwatask.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.nagwatask.R
import com.example.nagwatask.data.locale.response.FakeListResponse
import com.example.nagwatask.databinding.ItemFileBinding
import com.example.nagwatask.utility.Constants
import com.example.nagwatask.utility.extension.gone
import com.example.nagwatask.utility.extension.setResourceToImageView

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */

class FilesViewHolder(
  private val view: ItemFileBinding,
  private var onDownloadClicked: (FakeListResponse, Int) -> Unit
) : RecyclerView.ViewHolder(view.root) {

  fun bind(item: FakeListResponse) {
    view.fileDownloadImageView.setOnClickListener {
      onDownloadClicked.invoke(
        item,
        layoutPosition
      )
    }

    if (item.isDownloaded == true) {
      view.fileDownloadIndicatorTextView.text = itemView.context.getString(R.string.downloaded)
    }
    if (item.failedCount == 3) {
      view.fileDownloadImageView.gone()
      view.fileDownloadIndicatorTextView.text = itemView.context.getString(R.string.failed)
    }
    view.fileTitleTextView.text = item.name ?: "ERROR"
    when (item.type) {
      Constants.FileType.PDF -> view.fileTypeImageView.setResourceToImageView(R.drawable.ic_pdf)
      Constants.FileType.VIDEO -> view.fileTypeImageView.setResourceToImageView(R.drawable.ic_video)
    }
  }
}