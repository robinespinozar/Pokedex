package com.raerossi.pokedex.ui.features.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.pokedex.data.remote.model.Pokemon
import com.raerossi.pokedex.domain.usecases.GetFavoritesPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoritesPokemonsUseCase: GetFavoritesPokemonsUseCase
) : ViewModel() {

    private val _favoritesPokemonList = MutableLiveData<List<Pokemon>>()
    val favoritesPokemonList: LiveData<List<Pokemon>> = _favoritesPokemonList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            _isLoading.value = true
            delay(5000)
            //_favoritesPokemonList.value = getFavoritesPokemonsUseCase()
            _isLoading.value = false
        }
    }
}