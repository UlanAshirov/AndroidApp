package com.uli.androidapp.data.network.api

import com.uli.androidapp.domain.network.model.CharacterResult
import com.uli.androidapp.domain.network.model.MainResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("character")
    suspend fun getCharacter(
        @Query("page") page: Int? = null,
        @Query("name") name: String? = null,
        @Query("status") status: String? = null,
        @Query("gender") gender: String? = null
    ): Response<MainResponse<CharacterResult>>

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") id: Int
    ): CharacterResult
}