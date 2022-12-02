package com.uli.androidapp.domain.local.usecase

import com.uli.androidapp.domain.local.localRepository.LocalRepository
import com.uli.androidapp.domain.network.model.CharacterResult

class AddCharacterUseCase(private val repository: LocalRepository) {

    suspend fun addCharacter(listCharacter: List<CharacterResult>) {
        repository.addCharacter(listCharacter)
    }
}