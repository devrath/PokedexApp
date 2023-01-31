package com.istudio.pokedex.ui.screens.pokemon_list.composables

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.istudio.pokedex.data.remote.models.PokedexListEntry

@Composable
fun PokemonLazyList(
    pokemonList: LazyPagingItems<PokedexListEntry>,
    onItemClick:(PokedexListEntry)-> Unit,
    funcCallBackImageTarget:(Drawable) -> Unit={}
){
    val state = rememberLazyListState()
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        state = state
    ){

        items(pokemonList.itemSnapshotList.items.size) { position ->
            PokemonListItem(
                item=pokemonList.itemSnapshotList.items[position],
                onItemClick=onItemClick
            )
        }

        when (val state = pokemonList.loadState.refresh) { //FIRST LOAD
            is LoadState.Error -> {
                //TODO Error Item
                //state.error to get error message
            }
            is LoadState.Loading -> { // Loading UI
                //
            }
            else -> {}
        }

        when (val state = pokemonList.loadState.append) { // Pagination
            is LoadState.Error -> {
                //state.error to get error message
            }
            is LoadState.Loading -> { // Pagination Loading UI

            }
            else -> {}
        }
    }
}