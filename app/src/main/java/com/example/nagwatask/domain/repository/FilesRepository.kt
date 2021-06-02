package com.example.nagwatask.domain.repository

import com.example.nagwatask.domain.model.FilesResponse
import io.reactivex.Observable

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
interface FilesRepository {
  fun getFilesList(): Observable<List<FilesResponse>>
}