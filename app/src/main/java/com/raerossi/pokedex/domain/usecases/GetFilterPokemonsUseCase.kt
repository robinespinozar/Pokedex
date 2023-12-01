package com.raerossi.pokedex.domain.usecases

import com.raerossi.pokedex.data.PokemonRepository
import javax.inject.Inject

class GetFilterPokemonsUseCase @Inject constructor(private val repository: PokemonRepository) {
    suspend operator fun invoke(name: String) = repository.getFilterPokemons(name)
}