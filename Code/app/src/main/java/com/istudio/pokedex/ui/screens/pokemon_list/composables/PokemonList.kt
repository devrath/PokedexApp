package com.istudio.pokedex.ui.screens.pokemon_list.composables

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.istudio.pokedex.data.remote.models.PokedexListEntry

@Composable
fun PokemonLazyList(
    pokemonList: LazyPagingItems<PokedexListEntry>,
    onItemClick:(PokedexListEntry)-> Unit,
    funcCallBackImageTarget:(Drawable) -> Unit={}
){
    val state = rememberLazyGridState()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalArrangement = Arrangement.SpaceBetween,
        state = state
    ){
        items(pokemonList.itemCount) { position ->
            pokemonList[position]?.let {
                PokemonListItem(
                    item= it,
                    onItemClick=onItemClick
                )
            }
        }
    }
}