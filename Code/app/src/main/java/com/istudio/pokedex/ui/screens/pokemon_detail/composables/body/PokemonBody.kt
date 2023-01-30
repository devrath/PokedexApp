package com.istudio.pokedex.ui.screens.pokemon_detail.composables.body

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.istudio.pokedex.R
import com.istudio.pokedex.domain.states.PokemonDetailView
import com.istudio.pokedex.ui.screens.common.composables.PokemonErrorBlock
import com.istudio.pokedex.ui.screens.common.composables.PokemonLoaderBlock

@Composable
fun PokemonBody(
    pokemonInfo: PokemonDetailView,
    modifier: Modifier = Modifier,
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 200.dp,
    onRetryClick: () -> Unit = {}
) {
    val screenTopPadding = topPadding + pokemonImageSize / 2f
    val screenPadding = 16.dp
    val roundCornerValue = 10.dp
    val roundCornerShape = RoundedCornerShape(roundCornerValue)

    Box(
        modifier = Modifier
            // Leave a certain padding
            .padding(
                top = screenTopPadding,
                start = screenPadding,
                end = screenPadding,
                bottom = screenPadding
            )
            // Fill the remaining entire screen
            .fillMaxSize()
            // Provide shadow for the card
            .shadow(roundCornerValue, roundCornerShape)
            .clip(roundCornerShape)
            // Fill the background color for card container
            .background(MaterialTheme.colors.surface)
            // Leave a certain spacing for the inside contents
            .padding(16.dp)
    ) {

        when (pokemonInfo) {
            is PokemonDetailView.DisplayErrorView -> {
                //-> Error
                PokemonErrorBlock(
                    text = stringResource(id = R.string.str_something_went_wrong),
                    image = painterResource(R.drawable.ic_something_went_wrong),
                    onActionClick = onRetryClick
                )
            }
            is PokemonDetailView.DisplayLoadingView -> {
                //-> Loading
                PokemonLoaderBlock(modifier = modifier.align(Alignment.Center))
            }
            is PokemonDetailView.DisplayPokemonView -> {
                //-> Content
                PokemonDetailSection(
                    pokemonInfo = pokemonInfo.data,
                    modifier = modifier
                        .offset(y = (-20).dp)
                )
            }
        }
    }
}