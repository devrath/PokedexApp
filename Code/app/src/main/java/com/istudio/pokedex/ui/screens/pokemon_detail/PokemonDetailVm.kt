package com.istudio.pokedex.ui.screens.pokemon_detail

import androidx.lifecycle.ViewModel
import com.istudio.pokedex.data.remote.responses.Pokemon
import com.istudio.pokedex.domain.PokemonRepositoryFeature
import com.istudio.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonDetailVm @Inject constructor(
    private val repository: PokemonRepositoryFeature
): ViewModel(){

    suspend fun getPokemonInfo(pokemonName:String): Resource<Pokemon>{
        return repository.getPokemonInfo(pokemonName)
    }

}