package com.istudio.pokedex.ui.screens.pokemon_list.composables

import android.graphics.drawable.Drawable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.paging.compose.itemsIndexed
import com.istudio.pokedex.data.remote.models.PokedexListEntry
import com.istudio.pokedex.ui.screens.pokemon_list.PokemonListVm

/**
 * https://stackoverflow.com/a/73962911/1083093
 */
@Composable
fun PokemonLazyList(
    onItemClick:(PokedexListEntry)-> Unit,
    loadingScreenState:() -> Unit = {},
    errorScreenState:() -> Unit = {},
){

    val viewModel: PokemonListVm = hiltViewModel()
    val pokemonList =  viewModel.getPokemonList().collectAsLazyPagingItems()
    val state = rememberLazyGridState()

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = pokemonList,
            key = { unsplashImage ->
                unsplashImage.number
            }
        ) { pokemon ->
            pokemon?.let {
                PokemonListItem(
                    item=it,
                    onItemClick=onItemClick
                )
            }

            pokemonList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        LoadingView(modifier = Modifier.fillMaxSize())
                    }
                    loadState.append is LoadState.Loading -> {
                        LoadingView(modifier = Modifier.fillMaxSize())
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = pokemonList.loadState.refresh as LoadState.Error
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            modifier = Modifier.fillMaxSize(),
                            onClickRetry = { retry() }
                        )
                    }
                    loadState.append is LoadState.Error -> {
                        val e = pokemonList.loadState.append as LoadState.Error
                        ErrorItem(
                            message = e.error.localizedMessage!!,
                            onClickRetry = { retry() }
                        )
                    }
                }
            }
        }

    }
}


@Composable
fun LoadingView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorItem(
    message: String,
    modifier: Modifier = Modifier,
    onClickRetry: () -> Unit
) {
    Row(
        modifier = modifier.padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = message,
            maxLines = 1,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.h6,
            color = Color.Red
        )
        OutlinedButton(onClick = onClickRetry) {
            Text(text = "Try again")
        }
    }
}