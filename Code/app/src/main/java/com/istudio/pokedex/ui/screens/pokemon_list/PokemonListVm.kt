package com.istudio.pokedex.ui.screens.pokemon_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.istudio.pokedex.data.remote.models.PokedexListEntry
import com.istudio.pokedex.domain.PokemonRepositoryFeature
import com.istudio.pokedex.util.Constants.PAGE_SIZE
import com.istudio.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListVm @Inject constructor(
    private val repository: PokemonRepositoryFeature
): ViewModel() {

    private var curPage = 0
    // Holds the list of item data
    var pokemonList = mutableStateOf<List<PokedexListEntry>>(listOf())
    // Holds the error state
    var loadError = mutableStateOf("")

    init {
        loadPokemonPaginated()
    }

    fun loadPokemonPaginated() {
        viewModelScope.launch {
            // Ge the data from API
            val result = repository.getPokemonList(PAGE_SIZE, curPage * PAGE_SIZE)
            when(result) {
                is Resource.Success -> {
                    result.data?.results?.let {
                        val pokedexEntries = result.data.results.mapIndexed { index, entry ->
                            val number = if(entry.url.endsWith("/")) {
                                entry.url.dropLast(1).takeLastWhile { it.isDigit() }
                            } else {
                                entry.url.takeLastWhile { it.isDigit() }
                            }
                            //val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${number}.png"
                            val url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${number}.png"
                            PokedexListEntry(entry.name.capitalize(Locale.ROOT), url, number.toInt())
                        }
                        pokemonList.value += pokedexEntries
                    }
                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                }
                is Resource.Loading -> {

                }
            }
        }
    }

    /**
     * What it does: It calculates the dominant color based on a drawable
     * What it returns: Color as a function callback
     * @param drawable
     * @param onFinish
     */
    fun calcDominantColor(drawable: Drawable, onFinish: (Color) -> Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

}