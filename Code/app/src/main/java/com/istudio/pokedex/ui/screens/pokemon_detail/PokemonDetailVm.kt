package com.istudio.pokedex.ui.screens.pokemon_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.istudio.pokedex.data.remote.responses.Pokemon
import com.istudio.pokedex.domain.feature.PokemonRepositoryFeature
import com.istudio.pokedex.domain.states.PokemonDetailState
import com.istudio.pokedex.domain.states.PokemonDetailView
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

    private val _state = MutableSharedFlow<PokemonDetailView>()
    val state = _state.asSharedFlow()

    suspend fun getPokemonDetails(pokemonName:String) {
        viewModelScope.launch {
            when(val pokemonInfo = repository.getPokemonInfo(pokemonName)){
                is Resource.Error -> {
                    pokemonInfo.message?.let {
                        _state.emit(PokemonDetailView.DisplayErrorView(message = it))
                    }
                }
                is Resource.Loading -> {
                    _state.emit(PokemonDetailView.DisplayLoadingView)
                }
                is Resource.Success ->  {
                    pokemonInfo.data?.let {
                        _state.emit(PokemonDetailView.DisplayPokemonView(data = it))
                    }
                }
            }
        }
    }


}