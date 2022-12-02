package com.uli.androidapp.domain.network.usecases

import com.uli.androidapp.domain.network.model.CharacterResult
import com.uli.androidapp.domain.network.repository.RickAndMortyRepository

class GetCharacterByIdUseCase(private val repository: RickAndMortyRepository) {
    suspend fun getCharacterById(id: Int): CharacterResult = repository.getCharacterById(id)
}