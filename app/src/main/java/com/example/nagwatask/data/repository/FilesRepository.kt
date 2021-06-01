package com.example.nagwatask.data.repository

import com.example.nagwatask.data.locale.response.FakeListResponse
import io.reactivex.Observable
import java.util.UUID

/**
 * Authored by Abdelrahman Ahmed on 01 Jun, 2021.
 */
interface FilesRepository {
  fun getFilesList(): Observable<List<FakeListResponse>>
  fun downloadFile(item: FakeListResponse,tag:String)
}