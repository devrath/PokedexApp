package com.istudio.pokedex.data.remote.responses


import com.google.gson.annotations.SerializedName
import com.istudio.pokedex.data.remote.responses.GenerationI
import com.istudio.pokedex.data.remote.responses.GenerationIi
import com.istudio.pokedex.data.remote.responses.GenerationIii
import com.istudio.pokedex.data.remote.responses.GenerationIv
import com.istudio.pokedex.data.remote.responses.GenerationV
import com.istudio.pokedex.data.remote.responses.GenerationVi
import com.istudio.pokedex.data.remote.responses.GenerationVii
import com.istudio.pokedex.data.remote.responses.GenerationViii

data class Versions(
    @SerializedName("generation-i")
    val generationI: GenerationI,
    @SerializedName("generation-ii")
    val generationIi: GenerationIi,
    @SerializedName("generation-iii")
    val generationIii: GenerationIii,
    @SerializedName("generation-iv")
    val generationIv: GenerationIv,
    @SerializedName("generation-v")
    val generationV: GenerationV,
    @SerializedName("generation-vi")
    val generationVi: GenerationVi,
    @SerializedName("generation-vii")
    val generationVii: GenerationVii,
    @SerializedName("generation-viii")
    val generationViii: GenerationViii
)