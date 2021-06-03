package com.example.nagwatask.domain.repository

import com.example.nagwatask.data.model.FilesResponse
import com.example.nagwatask.domain.entity.FileEntity
import io.reactivex.Observable
import io.reactivex.Single

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
interface FilesRepository {
  fun getFilesList(): Single<List<FileEntity>>
}