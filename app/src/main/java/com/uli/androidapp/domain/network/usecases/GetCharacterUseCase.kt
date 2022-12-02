package com.uli.androidapp.domain.network.usecases

import androidx.paging.PagingData
import com.uli.androidapp.domain.network.model.CharacterResult
import com.uli.androidapp.domain.network.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow

class GetCharacterUseCase(private val repository: RickAndMortyRepository) {
    suspend fun getCharacter(name: String, status: String, gender: String)
            : Flow<PagingData<CharacterResult>> = repository.getCharacter(name, status, gender)
}