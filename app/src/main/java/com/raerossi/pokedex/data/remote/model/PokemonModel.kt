package com.raerossi.pokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class PokemonModel(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
)

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