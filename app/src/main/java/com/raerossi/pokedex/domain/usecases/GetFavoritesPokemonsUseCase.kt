package com.raerossi.pokedex.domain.usecases

import com.raerossi.pokedex.data.PokemonRepository
import javax.inject.Inject

class GetFavoritesPokemonsUseCase @Inject constructor(private val repository: PokemonRepository) {

    //suspend operator fun invoke(): List<Pokemon> = repository.getPokemons()
}