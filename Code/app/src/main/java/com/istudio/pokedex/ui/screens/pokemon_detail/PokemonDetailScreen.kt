package com.istudio.pokedex.ui.screens.pokemon_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.istudio.pokedex.domain.states.PokemonDetailState
import com.istudio.pokedex.ui.screens.pokemon_detail.composables.body.PokemonBody
import com.istudio.pokedex.ui.screens.pokemon_detail.composables.header.PokemonHeader
import com.istudio.pokedex.util.PokemonUtils
import kotlinx.coroutines.launch

@Composable
fun PokemonDetailScreen(
    dominantColor: Color,
    pokemonName: String,
    navController: NavController,
    topPadding: Dp = 20.dp,
    pokemonImageSize: Dp = 200.dp,
    viewModel: PokemonDetailVm = hiltViewModel()
) {

    var pokemonDetailData by remember {
        mutableStateOf(PokemonDetailState(
            data = null, isLoading = true,hasError = false,errorMessage = null
        ))
    }
    val pokemonDetailScope = rememberCoroutineScope()

    LaunchedEffect(key1 = true){
        viewModel.getPokemonDetails(pokemonName)
        viewModel.state.collect{
            pokemonDetailData = it
        }
    }

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
            pokemonInfo = pokemonDetailData,
            topPadding = topPadding,
            pokemonImageSize = pokemonImageSize
        ) {
            pokemonDetailScope.launch {
                viewModel.getPokemonDetails(pokemonName)
            }
        }

        Box(
            contentAlignment = Alignment.TopCenter,
            modifier = Modifier
                .fillMaxSize()
        ) {
            // Display the image if the API is successful
            if(!pokemonDetailData.hasError && !pokemonDetailData.isLoading){
                pokemonDetailData.data?.sprites?.let {
                    // Image is available
                    val url = PokemonUtils.formatPokemonDetailUrl(it.frontDefault)
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(url)
                            .crossfade(true)
                            .build(),
                        contentDescription = pokemonDetailData.data?.name,
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
