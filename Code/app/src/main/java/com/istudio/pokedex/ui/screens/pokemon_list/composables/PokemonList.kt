package com.istudio.pokedex.ui.screens.pokemon_list.composables

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.istudio.pokedex.data.remote.models.PokedexListEntry

@Composable
fun PokemonLazyList(
    pokemonList : List<PokedexListEntry>,
    onItemClick:(PokedexListEntry)-> Unit,
    funcCallBackImageTarget:(Drawable) -> Unit={}
){
    val state = rememberLazyListState()
    LazyColumn(
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        state = state
    ){
        items(pokemonList.size) { position ->
            PokemonListItem(
                item=pokemonList[position],
                onItemClick=onItemClick
            )
        }
    }
}