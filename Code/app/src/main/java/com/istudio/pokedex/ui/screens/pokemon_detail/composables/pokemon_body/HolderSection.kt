package com.istudio.pokedex.ui.screens.pokemon_detail.composables.pokemon_body

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.istudio.pokedex.data.remote.responses.Pokemon
import com.istudio.pokedex.ui.screens.pokemon_detail.composables.pokemon_body.stats.PokemonBaseStats
import com.istudio.pokedex.ui.screens.pokemon_detail.composables.pokemon_body.attributes.PokemonAttributes
import com.istudio.pokedex.ui.screens.pokemon_detail.composables.pokemon_body.name.PokemonName

@Composable
fun PokemonDetailSection(
    pokemonInfo: Pokemon,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .offset(y = 100.dp)
            .verticalScroll(scrollState)
    ) {
        PokemonName(id = pokemonInfo.id, name = pokemonInfo.name)
        PokemonTypes(types = pokemonInfo.types)
        Spacer(modifier = Modifier.height(20.dp))
        PokemonAttributes(
            pokemonWeight = pokemonInfo.weight,
            pokemonHeight = pokemonInfo.height
        )
        Spacer(modifier = Modifier.height(20.dp))
        PokemonBaseStats(pokemonInfo = pokemonInfo)
    }
}