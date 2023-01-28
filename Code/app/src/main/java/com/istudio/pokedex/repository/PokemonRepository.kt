package com.istudio.pokedex.repository

import com.istudio.pokedex.data.remote.responses.Pokemon
import com.istudio.pokedex.data.remote.responses.PokemonList
import com.istudio.pokedex.util.Resource

interface PokemonRepository {
    /**
     * Getting a list of pokemon
     */
    suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList>

    /**
     * Getting details of pokemon
     */
    suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon>
}