package com.istudio.pokedex.ui.screens.pokemon_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.istudio.pokedex.data.remote.models.PokedexListEntry
import com.istudio.pokedex.ui.screens.pokemon_list.composables.PokemonBanner
import com.istudio.pokedex.ui.screens.pokemon_list.composables.PokemonLazyList
import kotlinx.coroutines.launch

@Composable
fun PokemonListScreen(
    viewModel: PokemonListVm = hiltViewModel()
) {

    val scaffoldState = rememberScaffoldState()
    val scope = rememberCoroutineScope()

    val lazyPokemonItems: LazyPagingItems<PokedexListEntry> =
        viewModel.pokemonList.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        scaffoldState = scaffoldState
    ) { padding ->
        Column(
            Modifier.fillMaxSize().padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            PokemonBanner()
            Spacer(modifier = Modifier.height(10.dp))
            PokemonLazyList(
                pokemonList = lazyPokemonItems,
                onItemClick = { entry ->
                    scope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(
                            message = entry.pokemonName,
                            duration = SnackbarDuration.Short
                        )
                    }
                }
            )
        }
    }
}
