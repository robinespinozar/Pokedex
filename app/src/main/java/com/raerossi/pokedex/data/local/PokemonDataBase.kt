package com.raerossi.pokedex.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.raerossi.pokedex.data.local.dao.PokemonDao
import com.raerossi.pokedex.data.local.entities.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDataBase : RoomDatabase() {

    abstract fun getPokemonDao(): PokemonDao
}