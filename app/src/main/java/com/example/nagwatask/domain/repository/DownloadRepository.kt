package com.example.nagwatask.domain.repository

import androidx.work.WorkRequest
import com.example.nagwatask.domain.model.FilesResponse

/**
 * Authored by Abdelrahman Ahmed on 02 Jun, 2021.
 */
interface DownloadRepository {
  fun downloadFile(item: FilesResponse): WorkRequest?
}