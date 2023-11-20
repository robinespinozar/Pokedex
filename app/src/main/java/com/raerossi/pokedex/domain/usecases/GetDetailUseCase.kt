package com.raerossi.pokedex.domain.usecases

import com.raerossi.pokedex.data.PokemonRepository
import com.raerossi.pokedex.domain.PokemonDetail
import javax.inject.Inject

class GetDetailUseCase @Inject constructor(private val repository: PokemonRepository) {
    suspend operator fun invoke(idPokemon: Int): PokemonDetail? = repository.getDetails(idPokemon)
}