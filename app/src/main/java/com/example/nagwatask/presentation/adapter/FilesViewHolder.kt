package com.example.nagwatask.presentation.adapter

import android.util.Log
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
  private var onDownloadClicked: (FakeListResponse) -> Unit,
  private var onViewVideoClicked: (String?) -> Unit
) : RecyclerView.ViewHolder(view.root) {

  fun bind(item: FakeListResponse) {
    view.fileDownloadImageView.setOnClickListener {
      onDownloadClicked.invoke(item)
    }
    view.root.setOnClickListener {
      if (item.failedCount < 3) {
        if (item.fileUri.isNotEmpty())
          onViewVideoClicked.invoke(item.fileUri)
        else
          onDownloadClicked.invoke(item)
      }
    }
    if (item.isDownloaded) {
      view.fileDownloadImageView.gone()
      view.fileDownloadIndicatorTextView.text = itemView.context.getString(R.string.downloaded)
    }
    if (item.failedCount == 3) {
      view.fileDownloadIndicatorTextView.text = itemView.context.getString(R.string.failed)
      view.fileDownloadImageView.gone()
    }
    view.fileTitleTextView.text = item.name ?: "ERROR"
    when (item.type) {
      Constants.FileType.PDF -> view.fileTypeImageView.setResourceToImageView(R.drawable.ic_pdf)
      Constants.FileType.VIDEO -> view.fileTypeImageView.setResourceToImageView(R.drawable.ic_video)
    }
  }
}