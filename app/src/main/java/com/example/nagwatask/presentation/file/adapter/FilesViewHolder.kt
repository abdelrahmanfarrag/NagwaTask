package com.example.nagwatask.presentation.file.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.nagwatask.R
import com.example.nagwatask.domain.model.FilesResponse
import com.example.nagwatask.databinding.ItemFileBinding
import com.example.nagwatask.utility.Constants
import com.example.nagwatask.utility.extension.invisible
import com.example.nagwatask.utility.extension.setResourceToImageView
import com.example.nagwatask.utility.extension.visible

/**
 * Authored by Abdelrahman Ahmed on 31 May, 2021.
 */

class FilesViewHolder(
  private val view: ItemFileBinding,
  private var onDownloadClicked: (FilesResponse) -> Unit,
  private var onViewVideoClicked: (String?) -> Unit
) : RecyclerView.ViewHolder(view.root) {

  fun bind(item: FilesResponse) {
    view.fileDownloadImageView.setOnClickListener {
      if (item.failedCount < 3) {
        view.fileDownloadIndicatorTextView.text = itemView.context.getString(R.string.downloading)
        view.fileDownloadIndicatorTextView.visible()
        onDownloadClicked.invoke(item)
      }
    }
    view.root.setOnClickListener {
      if (item.failedCount < 3) {
        if (item.fileUri.isNotEmpty())
          onViewVideoClicked.invoke(item.fileUri)
        else {
          view.fileDownloadIndicatorTextView.visible()
          view.fileDownloadIndicatorTextView.text = itemView.context.getString(R.string.downloading)
          onDownloadClicked.invoke(item)
        }
      }
    }
    if (item.isDownloaded) {
      view.fileDownloadImageView.invisible()
      view.fileDownloadIndicatorTextView.text = itemView.context.getString(R.string.downloaded)
    }
    when (item.failedCount) {
      1, 2 -> {
        view.fileDownloadIndicatorTextView.text = itemView.context.getString(R.string.retry)
        view.fileDownloadImageView.setResourceToImageView(R.drawable.ic_retry)
        view.fileDownloadImageView.visible()
      }
      3 -> {
        view.fileDownloadIndicatorTextView.text = itemView.context.getString(R.string.failed)
        view.fileDownloadImageView.invisible()
      }
    }
    view.fileTitleTextView.text = item.name ?: ""
    when (item.type) {
      Constants.FileType.PDF -> view.fileTypeImageView.setResourceToImageView(R.drawable.ic_pdf)
      Constants.FileType.VIDEO -> view.fileTypeImageView.setResourceToImageView(R.drawable.ic_video)
    }
  }
}