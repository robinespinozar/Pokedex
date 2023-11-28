package com.raerossi.pokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String,
    val detail: PokemonDetail
) {
    fun getImageUrl(index :Int): String = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$index.png"

    fun getId(): Int {
        val arrayUrl = url.split("/")
        return arrayUrl[arrayUrl.size - 2].toInt()
    }

    fun getformatId(index :Int): String = "N° ${index.toString().padStart(3,'0')}"
}

data class PokemonDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("height") val height: Int,
    @SerializedName("weight") val weight: Int,
    @SerializedName("stats") val stats: List<Stats>,
    @SerializedName("types") val types: List<Types>,
    @SerializedName("sprites") val sprite: Sprite
) {
    fun getIdFormat(): String = String.format("N° %03d", id)
    fun getWeightFormat(): String = String.format("%.1f kg", weight.toFloat() / 10)
    fun getHeightFormat(): String = String.format("%.1f m", height.toFloat() / 10)
    fun getHpValue(): Float = (stats[0].baseStat) * 0.005f
    fun getAttackValue(): Float = (stats[1].baseStat) * 0.005f
    fun getDefenseValue(): Float = (stats[2].baseStat) * 0.005f
    fun getSpeedValue(): Float = (stats[5].baseStat) * 0.005f
    fun getHpFormat(): String {
        val hp = stats[0].baseStat
        return "$hp/200"
    }

    fun getAttackFormat(): String {
        val attack = stats[1].baseStat
        return "$attack/200"
    }

    fun getDefenseFormat(): String {
        val defense = stats[2].baseStat
        return "$defense/200"
    }

    fun getSpeedFormat(): String {
        val speed = stats[5].baseStat
        return "$speed/200"
    }
}

data class Stats(
    @SerializedName("base_stat") val baseStat: Int,
    @SerializedName("stat") val stat: Stat,
)

data class Stat(
    @SerializedName("name") val name: String
)

data class Types(
    @SerializedName("type") val type: Type
)

data class Type(
    @SerializedName("name") val name: String
)

data class Sprite(
    @SerializedName("other") val other: Other
)

data class Other(
    @SerializedName("official-artwork") val oficialArt: OficialArtwork
)

data class OficialArtwork(
    @SerializedName("front_default") val imagen: String
)