package com.raerossi.pokedex.domain.usecases

import com.raerossi.pokedex.data.PokemonRepository
import com.raerossi.pokedex.domain.Pokemon
import javax.inject.Inject

class GetFavoritesPokemonsUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(): List<Pokemon> = repository.getFavoritesPokemons()
}