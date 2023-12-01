package com.raerossi.pokedex.domain.usecases

import com.raerossi.pokedex.data.PokemonRepository
import javax.inject.Inject

class IsFavoritePokemonUseCase @Inject constructor(private val repository: PokemonRepository) {
    suspend operator fun invoke(id: Int) = repository.isFavoritePokemon(id)
}