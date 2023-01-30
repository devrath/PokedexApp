package com.istudio.pokedex.ui.screens.pokemon_detail.composables.pokemon_body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.istudio.pokedex.R
import com.istudio.pokedex.ui.screens.pokemon_detail.composables.PokemonDetailDataItem
import com.istudio.pokedex.util.PokemonUtils.weightHeightMeasurement

@Composable
fun PokemonAttributes(
    pokemonWeight: Int,
    pokemonHeight: Int,
    sectionHeight: Dp = 80.dp
) {
    val weight = remember { weightHeightMeasurement(pokemonWeight) }
    val height = remember { weightHeightMeasurement(pokemonHeight) }

    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        PokemonDetailDataItem(
            dataValue = weight,
            dataTag = stringResource(id = R.string.str_weight),
            dataUnit = stringResource(id = R.string.str_kg),
            dataIcon = painterResource(id = R.drawable.ic_pokemon_weight),
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier
            .size(1.dp, sectionHeight)
            .background(Color.LightGray))
        PokemonDetailDataItem(
            dataValue = height,
            dataTag = stringResource(id = R.string.str_height),
            dataUnit = stringResource(id = R.string.str_meter),
            dataIcon = painterResource(id = R.drawable.ic_pokemon_height),
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview
@Composable
private fun CurrentScreen() {
    PokemonAttributes(pokemonHeight = 1, pokemonWeight = 13)
}