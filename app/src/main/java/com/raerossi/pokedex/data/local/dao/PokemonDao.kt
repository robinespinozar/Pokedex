package com.raerossi.pokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raerossi.pokedex.data.local.Tables
import com.raerossi.pokedex.data.local.entities.PokemonEntity
import com.raerossi.pokedex.domain.Pokemon

@Dao
interface PokemonDao {

    @Query("Select * from " + Tables.POKEMON + " order by idPokemon desc")
    suspend fun getAllPokemons(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPokemons(listPokemons: List<PokemonEntity>)
}