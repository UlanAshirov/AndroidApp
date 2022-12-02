package com.uli.androidapp.ui.fragment.character

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.uli.androidapp.domain.local.usecase.AddCharacterUseCase
import com.uli.androidapp.domain.local.usecase.GetAllCharactersResultUseCase
import com.uli.androidapp.domain.network.model.CharacterResult
import com.uli.androidapp.domain.network.usecases.GetCharacterUseCase
import com.uli.androidapp.ui.fragment.character.state.CharacterState
import com.uli.androidapp.ui.utils.networkstate.ListenNetwork
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val getCharacterUseCase: GetCharacterUseCase,
    private val getAllCharactersResultUseCase: GetAllCharactersResultUseCase,
    private val addCharacterUseCase: AddCharacterUseCase,
    listenNetwork: ListenNetwork
) :
    ViewModel() {
    private val _state = MutableStateFlow(CharacterState())
    val isConnected: Flow<Boolean> = listenNetwork.isConnected

    init {
        viewModelScope.launch {
            getCharacter().collect {
                _state.value = _state.value.copy(results = it)
            }
        }
    }

    suspend fun getCharacter(): Flow<PagingData<CharacterResult>> {
        val name = _state.value.name.value
        val status = _state.value.status.value
        val gender = _state.value.gender.value
        return gender?.let {
            status?.let { it1 ->
                name?.let { it2 ->
                    getCharacterUseCase.getCharacter(
                        name = it2,
                        status = it1,
                        gender = it
                    )
                }
            }
        }!!
    }

    fun getAllCharactersResult(): Flow<List<CharacterResult>> {
        return getAllCharactersResultUseCase.getAllCharacters()
    }

    suspend fun addCharacterResult(listCharacter: List<CharacterResult>) {
        addCharacterUseCase.addCharacter(listCharacter)
    }

    fun setFilterCharacter(nameFilter: String, statusFilter: String, genderFilter: String) {
        _state.value.name.value = nameFilter
        _state.value.status.value = statusFilter
        _state.value.gender.value = genderFilter
    }

}