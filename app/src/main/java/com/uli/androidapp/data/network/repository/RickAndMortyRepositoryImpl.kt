package com.uli.androidapp.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.uli.androidapp.data.network.api.ApiService
import com.uli.androidapp.data.source.CharacterPagingSource
import com.uli.androidapp.domain.network.model.CharacterResult
import com.uli.androidapp.domain.network.repository.RickAndMortyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(private val api: ApiService)
    : RickAndMortyRepository {

    override suspend fun getCharacter(
        name: String,
        status: String,
        gender: String
    ): Flow<PagingData<CharacterResult>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                CharacterPagingSource(
                    api = api,
                    name = name,
                    status = status,
                    gender = gender
                )
            }
        ).flow
    }

    override suspend fun getCharacterById(id: Int): CharacterResult {
        return api.getCharacterById(id)
    }
}