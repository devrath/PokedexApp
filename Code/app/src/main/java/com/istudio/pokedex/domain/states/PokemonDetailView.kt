package com.istudio.pokedex.domain.states

import com.istudio.pokedex.data.remote.responses.Pokemon

sealed class PokemonDetailView {
    data class DisplayPokemonView(val data : Pokemon):PokemonDetailView()
    data class DisplayErrorView(val message : String):PokemonDetailView()
    object DisplayLoadingView : PokemonDetailView()
}
