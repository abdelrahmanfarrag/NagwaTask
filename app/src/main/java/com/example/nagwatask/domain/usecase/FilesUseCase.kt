package com.example.nagwatask.domain.usecase

import com.example.nagwatask.domain.entity.FileEntity
import com.example.nagwatask.domain.repository.FilesRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 02 Jun, 2021.
 */
class FilesUseCase @Inject constructor(private val filesRepository: FilesRepository) {

  fun getFiles(): Single<List<FileEntity>> = filesRepository.getFilesList()
}