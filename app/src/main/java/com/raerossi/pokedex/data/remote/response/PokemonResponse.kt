package com.raerossi.pokedex.data.remote.response

import com.google.gson.annotations.SerializedName
import com.raerossi.pokedex.data.remote.model.Pokemon

data class PokemonResponse(
    @SerializedName("results") val results: List<Pokemon>
)