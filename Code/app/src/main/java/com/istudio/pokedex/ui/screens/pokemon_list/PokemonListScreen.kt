package com.istudio.pokedex.ui.screens.pokemon_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.istudio.pokedex.ui.screens.pokemon_list.composables.PokemonBanner
import com.istudio.pokedex.ui.screens.pokemon_list.composables.PokemonLazyList

@Composable
fun PokemonListScreen(
    navController: NavController,
    viewModel: PokemonListVm = hiltViewModel()
) {

    val pokemonList = viewModel.getPokemonList().collectAsLazyPagingItems()


    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {

        Column {
            Spacer(modifier = Modifier.height(20.dp))
            PokemonBanner()
            PokemonLazyList(
                pokemonList = pokemonList,
                onItemClick = { entry ->
                    navController.navigate(
                        "pokemon_detail_screen/${entry.dominentColor.toArgb()}/${entry.pokemonName}"
                    )
                }
            )
        }
    }
}
