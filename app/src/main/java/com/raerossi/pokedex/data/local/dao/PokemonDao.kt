package com.raerossi.pokedex.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.raerossi.pokedex.data.local.Tables
import com.raerossi.pokedex.data.local.entities.PokemonEntity
import com.raerossi.pokedex.domain.Pokemon

@Dao
interface PokemonDao {

    @Query("Select * from " + Tables.POKEMON + " order by name desc")
    suspend fun getAllPokemons(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPokemons(listPokemons: List<PokemonEntity>)

    @Query("Delete from " + Tables.POKEMON)
    suspend fun deleteAllPokemons()

    @Query("Select * from " + Tables.POKEMON + " where isFavorite =1 order by datetime(fechaAddFavorite) asc")
    suspend fun getFavoritesPokemons(): List<PokemonEntity>

    @Query("Select isFavorite from " + Tables.POKEMON + " where idPokemon =:idPokemon")
    suspend fun isFavoritePokemon(idPokemon: Int): Boolean

    @Query("Update " + Tables.POKEMON + " set isFavorite =:isFavorite, fechaAddFavorite=:fechaAddFavorite where idPokemon=:idPokemon")
    suspend fun updateFavoritePokemon(isFavorite: Boolean, idPokemon: Int, fechaAddFavorite: String?)

    @Query("Select * from "+ Tables.POKEMON + " where name like '%' || :name || '%'")
    suspend fun getFilterPokemons(name: String): List<Pokemon>
}