package com.raerossi.pokedex.data.remote

import com.raerossi.pokedex.domain.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonService @Inject constructor(private val pokemonClient: PokemonClient) {

    suspend fun getPokemons(): List<Pokemon>{
        return withContext(Dispatchers.IO){
            val response = pokemonClient.getPokemons()
            response.body()?.results ?:emptyList()
        }
    }
}