package com.raerossi.pokedex.data.remote

import com.raerossi.pokedex.data.remote.response.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonClient {
    @GET("pokemon?limit=1281")
    suspend fun getPokemons(): Response<PokemonResponse>
}