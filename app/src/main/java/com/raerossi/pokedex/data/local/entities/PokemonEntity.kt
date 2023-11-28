package com.raerossi.pokedex.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.raerossi.pokedex.data.local.Tables

@Entity(tableName = Tables.POKEMON)
data class PokemonEntity(
    @PrimaryKey
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "idPokemon") val idPokemon: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "url") val url: String
)