package com.uli.androidapp.data.local.repository

import com.uli.androidapp.data.local.db.CharacterDao
import com.uli.androidapp.domain.local.localRepository.LocalRepository
import com.uli.androidapp.domain.network.model.CharacterResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalRepositoryImpl @Inject constructor(private val dao: CharacterDao) : LocalRepository {

    override fun getAllCharacters(): Flow<List<CharacterResult>> {
        return dao.getAllCharacterResult()
    }

    override suspend fun addCharacter(listCharacter: List<CharacterResult>) {
        dao.addCharacter(listCharacter)
    }
}
