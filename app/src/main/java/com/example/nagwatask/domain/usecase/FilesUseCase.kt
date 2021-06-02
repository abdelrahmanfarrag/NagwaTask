package com.example.nagwatask.domain.usecase

import com.example.nagwatask.domain.repository.FilesRepository
import javax.inject.Inject

/**
 * Authored by Abdelrahman Ahmed on 02 Jun, 2021.
 */
class FilesUseCase @Inject constructor(private val filesRepository: FilesRepository) {

  fun getFiles() = filesRepository.getFilesList()
}