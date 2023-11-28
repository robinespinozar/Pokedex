package com.raerossi.pokedex.data.remote

import com.raerossi.pokedex.data.remote.model.PokemonDetail
import com.raerossi.pokedex.data.remote.model.PokemonModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonService @Inject constructor(private val pokemonClient: PokemonClient) {

    suspend fun getPokemons(): List<PokemonModel>{
        return withContext(Dispatchers.IO){
            val response = pokemonClient.getPokemons()
            response.body()?.results ?:emptyList()
        }
    }

    suspend fun getDetail(idPokemon: Int): PokemonDetail?{
        return withContext(Dispatchers.IO){
            val response = pokemonClient.getDetail(idPokemon)
            response.body()
        }
    }
}