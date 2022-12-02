package com.uli.androidapp.ui.fragment.character.state

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import com.uli.androidapp.domain.network.model.CharacterResult

data class CharacterState(
    val results: PagingData<CharacterResult>? = PagingData.empty(),
    val name: MutableLiveData<String> = MutableLiveData(""),
    val status: MutableLiveData<String> = MutableLiveData(""),
    val gender: MutableLiveData<String> = MutableLiveData(""),
)