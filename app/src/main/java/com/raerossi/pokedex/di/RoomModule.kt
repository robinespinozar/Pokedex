package com.raerossi.pokedex.di

import android.content.Context
import androidx.room.Room
import com.raerossi.pokedex.data.local.PokemonDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val POKEDEX_DATABASE = "pokedex_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, PokemonDataBase::class.java, POKEDEX_DATABASE)

    @Singleton
    @Provides
    fun providePokemonDao(database: PokemonDataBase) = database.getPokemonDao()
}