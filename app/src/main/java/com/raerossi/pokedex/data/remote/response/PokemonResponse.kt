package com.raerossi.pokedex.data.remote.response

import com.google.gson.annotations.SerializedName
import com.raerossi.pokedex.data.remote.model.PokemonModel

data class PokemonResponse(
    @SerializedName("results") val results: List<PokemonModel>
)