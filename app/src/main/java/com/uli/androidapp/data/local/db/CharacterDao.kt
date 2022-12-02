package com.uli.androidapp.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.uli.androidapp.domain.network.model.CharacterResult
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characterresult")
    fun getAllCharacterResult(): Flow<List<CharacterResult>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCharacter(listCharacter: List<CharacterResult>)

}
