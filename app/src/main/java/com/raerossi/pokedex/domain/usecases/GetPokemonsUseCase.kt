package com.raerossi.pokedex.domain.usecases

import com.raerossi.pokedex.data.PokemonRepository
import com.raerossi.pokedex.data.local.entities.toDataBase
import com.raerossi.pokedex.domain.Pokemon
import javax.inject.Inject

class GetPokemonsUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(): List<Pokemon> {
        val listPokemons = repository.getPokemonsFromApi()

        return if (listPokemons.isEmpty()) {
            repository.getPokemonsFromDataBase()
        } else {
            val listFavorites = repository.getFavoritesPokemons()
            repository.clearAllPokemons()
            repository.insertAllPokemons(listPokemons.map { it.toDataBase() })
            repository.updateFavoritePokemons(listFavorites)
            listPokemons
        }
    }
}