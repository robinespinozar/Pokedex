package com.raerossi.pokedex.domain

data class Pokemon(
    val name: String,
    val url: String
) {
    fun getImageUrl(index :Int): String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"

    fun getId(): Int {
        val arrayUrl = url.split("/")
        return arrayUrl[arrayUrl.size - 2].toInt()
    }

    fun getformatId(index :Int): String = "N° ${index.toString().padStart(3,'0')}"
}