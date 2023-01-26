package com.istudio.pokedex.ui.screens.pokemon_list.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.istudio.pokedex.R

@Composable
fun PokemonBanner() {
    Image(
        painterResource(id = R.drawable.ic_international_pok_mon_logo),
        contentDescription = "Pokemon",
        modifier = Modifier.fillMaxWidth()
    )
}