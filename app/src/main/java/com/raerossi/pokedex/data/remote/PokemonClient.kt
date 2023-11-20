package com.raerossi.pokedex.data.remote

import com.raerossi.pokedex.data.remote.response.PokemonResponse
import com.raerossi.pokedex.domain.PokemonDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonClient {
    @GET("pokemon?limit=1281")
    suspend fun getPokemons(): Response<PokemonResponse>

    @GET("pokemon/{name}")
    suspend fun getDetail(@Path("name") name: Int): Response<PokemonDetail>
}