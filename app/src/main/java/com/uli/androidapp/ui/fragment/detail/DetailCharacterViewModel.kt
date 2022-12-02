package com.uli.androidapp.ui.fragment.detail

import androidx.lifecycle.ViewModel
import com.uli.androidapp.domain.network.usecases.GetCharacterByIdUseCase
import com.uli.androidapp.ui.utils.networkstate.ListenNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class DetailCharacterViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    listenNetwork: ListenNetwork
) :
    ViewModel() {

    val isConnected: Flow<Boolean> = listenNetwork.isConnected
    suspend fun getCharacterById(id: Int) =
        flow { emit(getCharacterByIdUseCase.getCharacterById(id)) }
}