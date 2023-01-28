package com.istudio.pokedex.ui.screens.pokemon_list.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.istudio.pokedex.R

@Composable
fun PokemonBanner(
    modifier: Modifier = Modifier
) {
    Image(
        painterResource(id = R.drawable.ic_international_pok_mon_logo),
        contentDescription = "Pokemon",
        modifier = Modifier.fillMaxWidth()
    )
}