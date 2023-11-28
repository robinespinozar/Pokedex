package com.raerossi.pokedex.data

import com.raerossi.pokedex.data.local.dao.PokemonDao
import com.raerossi.pokedex.data.remote.PokemonService
import com.raerossi.pokedex.data.remote.model.Pokemon
import com.raerossi.pokedex.data.remote.model.PokemonDetail
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonService,
    private val dao: PokemonDao
) {

    suspend fun getPokemons(): List<Pokemon> {
        return api.getPokemons()
    }

    suspend fun getDetails(idPokemon: Int): PokemonDetail? {
        return api.getDetail(idPokemon)
    }
}