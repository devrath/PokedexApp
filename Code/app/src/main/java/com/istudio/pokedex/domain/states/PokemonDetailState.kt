package com.istudio.pokedex.domain.states

import com.istudio.pokedex.data.remote.responses.Pokemon
import com.istudio.pokedex.util.Resource

data class PokemonDetailState(
    val data: Pokemon? = null,
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val errorMessage: String? = null
)
