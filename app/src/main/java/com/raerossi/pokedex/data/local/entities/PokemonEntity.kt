package com.raerossi.pokedex.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raerossi.pokedex.data.local.Tables
import com.raerossi.pokedex.domain.Pokemon

@Entity(tableName = Tables.POKEMON)
data class PokemonEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    //@ColumnInfo(name = "idPokemon") val idPokemon: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String
    //@ColumnInfo(name = "isFavorite") val isFavorite: Boolean
)

fun Pokemon.toDataBase() = PokemonEntity(name = name, url = url)