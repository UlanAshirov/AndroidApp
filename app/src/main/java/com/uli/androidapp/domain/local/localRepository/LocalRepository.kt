package com.uli.androidapp.domain.local.localRepository

import com.uli.androidapp.domain.network.model.CharacterResult
import kotlinx.coroutines.flow.Flow

interface LocalRepository {
    fun getAllCharacters(): Flow<List<CharacterResult>>
    suspend fun addCharacter(listCharacter: List<CharacterResult>)
}