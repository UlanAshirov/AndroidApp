package com.uli.androidapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.uli.androidapp.domain.network.model.CharacterResult

@Database(entities = [CharacterResult::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {
    abstract fun getDao(): CharacterDao
}
