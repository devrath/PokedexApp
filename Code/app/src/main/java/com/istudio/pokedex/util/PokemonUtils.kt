package com.istudio.pokedex.util

import com.istudio.pokedex.data.remote.responses.Result
import java.util.Locale

object PokemonUtils {

    fun formatPokemonUrl(pokemonUrl: String): String {
        val number = if (pokemonUrl.endsWith("/")) {
            pokemonUrl.dropLast(1).takeLastWhile { it.isDigit() }
        } else {
            pokemonUrl.takeLastWhile { it.isDigit() }
        }
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${number}.png"
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

    fun capitalizeText(text :String): String {
        return text.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.ROOT) else it.toString() }
    }


}