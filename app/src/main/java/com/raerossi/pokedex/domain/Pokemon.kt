package com.raerossi.pokedex.domain

import com.raerossi.pokedex.data.local.entities.PokemonEntity
import com.raerossi.pokedex.data.remote.model.PokemonModel

data class Pokemon(
    val name: String,
    val url: String,
    val isFavorite: Boolean = false,
    val fechaAddFavorite: String? = null
) {

    fun getImageUrl(index: Int): String =
        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"

    fun getId(): Int {
        val arrayUrl = url.split("/")
        return arrayUrl[arrayUrl.size - 2].toInt()
    }

    fun getformatId(index: Int): String = "N° ${index.toString().padStart(3, '0')}"
}

fun PokemonModel.toDomain() = Pokemon(name, url)
fun PokemonEntity.toDomain() = Pokemon(name, url, isFavorite, fechaAddFavorite)