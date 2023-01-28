package com.istudio.pokedex.ui.screens.pokemon_list

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.palette.graphics.Palette
import com.istudio.pokedex.data.remote.PokeApi
import com.istudio.pokedex.data.remote.models.PokedexListEntry
import com.istudio.pokedex.repository.PokemonRepository
import com.istudio.pokedex.repository.paged.PokemonSource
import com.istudio.pokedex.util.Constants.PAGE_SIZE
import com.istudio.pokedex.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class PokemonListVm @Inject constructor(
    private val repository: PokemonRepository,
    private val api: PokeApi
): ViewModel() {

    val pokemonList = Pager(PagingConfig(pageSize = PAGE_SIZE)) {
        PokemonSource(api)
    }.flow

}