package com.raerossi.pokedex.ui.features.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.pokedex.domain.Pokemon
import com.raerossi.pokedex.domain.usecases.GetFilterPokemonsUseCase
import com.raerossi.pokedex.domain.usecases.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getFilterPokemonsUseCase: GetFilterPokemonsUseCase
) : ViewModel() {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _filterName = MutableLiveData<String>()
    val filterName: LiveData<String> = _filterName

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            _isLoading.value = true
            _pokemonList.value = getPokemonsUseCase()
            _isLoading.value = false
        }
    }

    fun getFilterPokemons(filterName: String){
        viewModelScope.launch {
            _isLoading.value = true
            _filterName.value = filterName
            _pokemonList.value = getFilterPokemonsUseCase(filterName.trim())
            _isLoading.value = false
        }
    }
}