package com.istudio.pokedex.util

import com.istudio.pokedex.data.remote.responses.Result

object PokemonUtils {

    fun formatPokemonUrl(pokemonUrl: String): String {
        val number = if (pokemonUrl.endsWith("/")) {
            pokemonUrl.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            pokemonUrl.takeLastWhile { it.isDigit() }
        }
        //val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${number}.png"

        // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png
        // https://pokeapi.co/api/v2/pokemon/1/
        // https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${number}.png
    }

    fun formatPokemonDetailUrl(pokemonUrl: String): String {
        return pokemonUrl.replace("pokemon","pokemon/other/official-artwork")
    }

    fun getPokemonNumber(entry: Result): String {
        val number = if(entry.url.endsWith("/")) {
            entry.url.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            entry.url.takeLastWhile { it.isDigit() }
        }
        return number
    }


}