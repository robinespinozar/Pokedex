package com.raerossi.pokedex.domain.usecases

import com.raerossi.pokedex.data.PokemonRepository
import com.raerossi.pokedex.utils.extensions.customFormat
import java.time.LocalDateTime
import javax.inject.Inject

class UpdateFavoritePokemonUseCase @Inject constructor(private val repository: PokemonRepository) {
    suspend operator fun invoke(isFavorite: Boolean, id: Int) = repository.updateFavoritePokemon(isFavorite, id, getCurrentDatetime())

    private fun getCurrentDatetime() = LocalDateTime.now().customFormat()
}