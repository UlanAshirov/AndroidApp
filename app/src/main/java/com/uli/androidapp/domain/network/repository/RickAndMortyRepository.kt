package com.uli.androidapp.domain.network.repository

import androidx.paging.PagingData
import com.uli.androidapp.domain.network.model.CharacterResult
import kotlinx.coroutines.flow.Flow

interface RickAndMortyRepository {
    suspend fun getCharacter(
        name: String,
        status: String,
        gender: String
    ): Flow<PagingData<CharacterResult>>

    suspend fun getCharacterById(id: Int): CharacterResult
}