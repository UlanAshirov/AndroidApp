package com.uli.androidapp.domain.network.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

data class MainResponse<T>(
    val info: Info,
    val results: List<T>
)

data class Info(
    val count: Int,
    val next: String,
    val pages: Int,
    val prev: String
)

@Entity
data class CharacterResult(
    var name: String = "",
    var image: String? = "",
    @PrimaryKey
    var id: Int? = 0,
    var status: String? = "",
    var gender: String? = "",
    @Ignore
    var location: Location? = null
    )

data class Location(
    val name: String,
    val url: String
)
