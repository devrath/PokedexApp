package com.istudio.pokedex.ui.screens.pokemon_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.pokedex.data.remote.responses.Pokemon
import com.istudio.pokedex.domain.feature.PokemonRepositoryFeature
import com.istudio.pokedex.domain.states.PokemonDetailState
import com.istudio.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailVm @Inject constructor(
    private val repository: PokemonRepositoryFeature
): ViewModel(){

    private val _state = MutableSharedFlow<PokemonDetailState>()
    val state = _state.asSharedFlow()

    /*suspend fun getPokemonInfo(pokemonName:String): Resource<Pokemon>{
        return repository.getPokemonInfo(pokemonName)
    }
    */

    suspend fun getPokemonDetails(pokemonName:String) {
        viewModelScope.launch {
            val pokemonInfo = repository.getPokemonInfo(pokemonName)
            when(pokemonInfo){
                is Resource.Error -> {
                    _state.emit(
                        PokemonDetailState(
                            data = null, isLoading = true,
                            hasError = true, errorMessage = pokemonInfo.message
                        )
                    )
                }
                is Resource.Loading -> {
                    _state.emit(
                        PokemonDetailState(
                            data = null, isLoading = true,
                            hasError = false, errorMessage = null
                        )
                    )
                }
                is Resource.Success ->  {
                    _state.emit(
                        PokemonDetailState(
                            data = pokemonInfo.data, isLoading = false,
                            hasError = false, errorMessage = null
                        )
                    )
                }
            }
        }
    }


}