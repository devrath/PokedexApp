package com.istudio.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName
import com.istudio.pokedex.data.remote.responses.BlackWhite

data class GenerationV(
    @SerializedName("black-white")
    val blackWhite: BlackWhite
)