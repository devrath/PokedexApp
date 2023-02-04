package com.istudio.pokedex.ui.screens.pokemon_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.palette.graphics.Palette
import com.istudio.pokedex.data.remote.models.PokedexListEntry
import com.istudio.pokedex.data.remote.paging.PagingRepository
import com.istudio.pokedex.domain.feature.PokemonRepositoryFeature
import com.istudio.pokedex.util.Constants.PAGE_SIZE
import com.istudio.pokedex.util.PokemonUtils
import com.istudio.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListVm @Inject constructor(
    private val repository: PagingRepository
): ViewModel() {

    fun getPokemonList(): Flow<PagingData<PokedexListEntry>> = repository.getPokemon().cachedIn(viewModelScope)


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