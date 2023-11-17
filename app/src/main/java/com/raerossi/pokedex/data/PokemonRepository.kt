package com.raerossi.pokedex.data

import com.raerossi.pokedex.data.remote.PokemonClient
import com.raerossi.pokedex.data.remote.PokemonService
import com.raerossi.pokedex.domain.Pokemon
import javax.inject.Inject

class PokemonRepository @Inject constructor(private val api: PokemonService) {

    suspend fun getPokemons(): List<Pokemon> {
        return api.getPokemons()
    }
}