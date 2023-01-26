package com.istudio.pokedex.ui.screens.pokemon_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.istudio.pokedex.R

@Composable
fun PokemonListScreen(
    navController: NavController
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(20.dp))
            Image(
                painterResource(id = R.drawable.ic_international_pok_mon_logo),
                contentDescription = "Pokemon",
                modifier = Modifier.fillMaxWidth().align(CenterHorizontally)
            )
            SearchBar(
                hint = "Search",
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ){
                /**
                 * This function gets called when someone enters something
                 * From here, We can call a function in the view model
                 */

            }
        }
    }
}

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    hint: String = "",
    onSearch: (String) -> Unit = {}
) {
    // Value for actual text
    var text by remember { mutableStateOf("") }
    // The boolean value will be true if hint displayed is true els it will be false
    var isHintDisplayed by remember { mutableStateOf(hint != "") }

    Box(modifier = modifier) {
        BasicTextField(
            value = text,
            onValueChange = {
                // Assign the new text value
                text = it
                // Function that we trigger on value changed
                onSearch(it)
            },
            maxLines = 1,
            singleLine = true,
            textStyle = TextStyle(color = Color.Black),
            modifier = Modifier
                .fillMaxWidth()
                .shadow(5.dp, CircleShape)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, 12.dp)
                .onFocusChanged {
                    /**
                     * User clicked the text field, So we can hide the hint here
                     * * If hte text field has focus we hide the hint & else show it
                     */
                    isHintDisplayed = !it.hasFocus
                }
        )
        if (isHintDisplayed) {
            Text(
                text = text,
                color = Color.LightGray,
                modifier = Modifier
                    .padding(horizontal = 20.dp, 12.dp)
            )
        }
    }
}