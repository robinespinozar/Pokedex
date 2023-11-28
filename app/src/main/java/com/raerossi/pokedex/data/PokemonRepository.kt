package com.raerossi.pokedex.data

import com.raerossi.pokedex.data.local.dao.PokemonDao
import com.raerossi.pokedex.data.local.entities.PokemonEntity
import com.raerossi.pokedex.data.remote.PokemonService
import com.raerossi.pokedex.data.remote.model.PokemonDetail
import com.raerossi.pokedex.domain.Pokemon
import com.raerossi.pokedex.domain.toDomain
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val api: PokemonService,
    private val dao: PokemonDao
) {

    suspend fun getPokemonsFromApi(): List<Pokemon> {
        val response = api.getPokemons()
        return response.map { it.toDomain() }
    }

    suspend fun getPokemonsFromDataBase(): List<Pokemon> {
        val response = dao.getAllPokemons()
        return response.map { it.toDomain() }
    }

    suspend fun insertAllPokemons(listPokemons: List<PokemonEntity>){
        dao.insertAllPokemons(listPokemons)
    }

    suspend fun clearAllPokemons(){
        dao.deleteAllPokemons()
    }

    suspend fun getDetails(idPokemon: Int): PokemonDetail? {
        return api.getDetail(idPokemon)
    }
}