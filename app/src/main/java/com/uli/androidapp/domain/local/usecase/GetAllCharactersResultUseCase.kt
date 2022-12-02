package com.uli.androidapp.domain.local.usecase

import com.uli.androidapp.domain.local.localRepository.LocalRepository
import com.uli.androidapp.domain.network.model.CharacterResult
import kotlinx.coroutines.flow.Flow

class GetAllCharactersResultUseCase(private val repository: LocalRepository) {

    fun getAllCharacters(): Flow<List<CharacterResult>> {
        return repository.getAllCharacters()
    }
}