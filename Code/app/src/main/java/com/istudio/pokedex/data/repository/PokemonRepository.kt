package com.istudio.pokedex.data.repository

import com.istudio.pokedex.domain.PokemonRepositoryFeature

open class PokemonRepository(
    private val feature : PokemonRepositoryFeature
) {

    open suspend fun getPokemonList(limit: Int, offset: Int) {
        feature.getPokemonList(limit = limit,offset = offset)
    }

    open suspend fun getPokemonList(pokemonName: String) {
        feature.getPokemonInfo(pokemonName = pokemonName)
    }

}