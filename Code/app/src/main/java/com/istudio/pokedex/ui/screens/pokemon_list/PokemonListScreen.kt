package com.istudio.pokedex.ui.screens.pokemon_list

import androidx.compose.foundation.background
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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.istudio.pokedex.data.remote.models.PokedexListEntry
import com.istudio.pokedex.ui.screens.pokemon_list.composables.PokemonBanner
import com.istudio.pokedex.ui.screens.pokemon_list.composables.PokemonLazyList

@Composable
fun PokemonListScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colors.background)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        PokemonBanner()
        PokemonLazyList(
            onItemClick = { entry ->
                navController.navigate(
                    "pokemon_detail_screen/${entry.dominentColor.toArgb()}/${entry.pokemonName}"
                )
            }
        )
    }
}
