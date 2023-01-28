package com.istudio.pokedex.data.implementation

import com.istudio.pokedex.data.remote.PokeApi
import com.istudio.pokedex.data.remote.responses.Pokemon
import com.istudio.pokedex.data.remote.responses.PokemonList
import com.istudio.pokedex.domain.PokemonRepositoryFeature
import com.istudio.pokedex.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

/**
 * This repository is activity scoped because the repository lies till the activity is activity is in vicinity
 * We inject the PokeApi in the constructor
 */
class PokemonRepositoryFeatureImpl @Inject constructor(
   private val api: PokeApi
) : PokemonRepositoryFeature {
    override suspend fun getPokemonList(limit: Int, offset: Int): Resource<PokemonList> {
        val response = try {
            api.getPokemonList(limit, offset)
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }

    override suspend fun getPokemonInfo(pokemonName: String): Resource<Pokemon> {
        val response = try {
            api.getPokemonInfo(pokemonName)
        } catch(e: Exception) {
            return Resource.Error("An unknown error occured.")
        }
        return Resource.Success(response)
    }
}