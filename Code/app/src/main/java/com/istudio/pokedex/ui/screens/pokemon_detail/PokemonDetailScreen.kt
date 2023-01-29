package com.istudio.pokedex.ui.screens.pokemon_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.istudio.pokedex.data.remote.responses.Pokemon
import com.istudio.pokedex.ui.screens.pokemon_detail.composables.PokemonDetailSection
import com.istudio.pokedex.ui.screens.pokemon_detail.composables.body.PokemonBody
import com.istudio.pokedex.ui.screens.pokemon_detail.composables.header.PokemonHeader
import com.istudio.pokedex.util.PokemonUtils
import com.istudio.pokedex.util.Resource

@Composable
fun PokemonDetailScreen(
    dominantColor: Color,
    pokemonName: String,
    navController: NavController,
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 200.dp,
    viewModel: PokemonDetailVm = hiltViewModel()
) {
    /**
     * This takes a initial state and with that we get a coroutine scope where we can call a API and assign the data into the value
     */
    val pokemonInfo = produceState<Resource<Pokemon>>(initialValue = Resource.Loading()) {
        value = viewModel.getPokemonInfo(pokemonName)
    }.value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(dominantColor)
            .padding(bottom = 16.dp)
    ) {

        PokemonHeader(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .align(Alignment.TopCenter)
        ) {
            navController.popBackStack()
        }

        PokemonBody(
            pokemonInfo = pokemonInfo,
            topPadding = topPadding,
            pokemonImageSize = pokemonImageSize,
            onRetryClick = {

            }
        )

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Display the image if the API is successful
            if (pokemonInfo is Resource.Success) {
                pokemonInfo.data?.sprites?.let {
                    // Image is available
                    val url = PokemonUtils.formatPokemonDetailUrl(it.frontDefault)
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(url)
                            .crossfade(true)
                            .build(),
                        contentDescription = pokemonInfo.data.name,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            // Set the default size passed to the composable
                            .size(pokemonImageSize)
                            // Shift the image down from the top
                            .offset(y = topPadding)
                    )
                }
            }
        }
    }
}