package com.istudio.pokedex.ui.screens.pokemon_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.istudio.pokedex.ui.screens.pokemon_list.composables.PokemonBanner
import com.istudio.pokedex.ui.screens.pokemon_list.composables.PokemonLazyList
import com.istudio.pokedex.ui.screens.pokemon_list.composables.PokemonSearch

@Composable
fun PokemonListScreen(
    navController: NavController
) {

    val viewModel = hiltViewModel<PokemonListVm>()
    val pokemonList by remember { viewModel.pokemonList }
    val endReached by remember { viewModel.endReached }
    val loadError by remember { viewModel.loadError }
    val isLoading by remember { viewModel.isLoading }
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {

        Column {
            Spacer(modifier = Modifier.height(20.dp))
            PokemonBanner()
            PokemonSearch()
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
