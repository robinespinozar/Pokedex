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

    suspend fun insertAllPokemons(listPokemons: List<PokemonEntity>) {
        dao.insertAllPokemons(listPokemons)
    }

    suspend fun clearAllPokemons() {
        dao.deleteAllPokemons()
    }

    suspend fun updateFavoritePokemons(listFavorites: List<Pokemon>) {
        listFavorites.forEach {
            dao.updateFavoritePokemon(true, it.getId(), it.fechaAddFavorite)
        }
    }

    suspend fun getFavoritesPokemons(): List<Pokemon> {
        val response = dao.getFavoritesPokemons()
        return response.map { it.toDomain() }
    }

    suspend fun isFavoritePokemon(id: Int): Boolean {
        return dao.isFavoritePokemon(id)
    }

    suspend fun updateFavoritePokemon(isFavorite: Boolean, id: Int, fechaAddFavorite: String) {
        dao.updateFavoritePokemon(isFavorite, id, fechaAddFavorite)
    }

    suspend fun getDetails(idPokemon: Int): PokemonDetail? {
        return api.getDetail(idPokemon)
    }

    suspend fun getFilterPokemons(name: String): List<Pokemon> {
        return dao.getFilterPokemons(name)
    }
}