package com.raerossi.pokedex.ui.features.home

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.raerossi.pokedex.domain.Pokemon
import com.raerossi.pokedex.domain.PokemonDetail
import com.raerossi.pokedex.domain.usecases.GetDetailUseCase
import com.raerossi.pokedex.domain.usecases.GetPokemonsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val getDetailUseCase: GetDetailUseCase
) :
    ViewModel() {

    private val _pokemonList = MutableLiveData<List<Pokemon>>()
    val pokemonList: LiveData<List<Pokemon>> = _pokemonList

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isSheetOpen = MutableLiveData<Boolean>()
    val isSheetOpen: LiveData<Boolean> = _isSheetOpen

    private val _detail = MutableLiveData<PokemonDetail>()
    val detail: LiveData<PokemonDetail> = _detail

    private val _isSheetLoading = MutableLiveData<Boolean>()
    val isSheetLoading: LiveData<Boolean> = _isSheetLoading

    init {
        viewModelScope.launch {
            _isLoading.value = true
            _pokemonList.value = getPokemonsUseCase()
            _isLoading.value = false
        }
    }

    fun getBackgroundColor(drawabale: Drawable, onFinish: (Color) -> Unit) {
        val bitmap = (drawabale as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bitmap).generate { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }
    }

    fun openBottomSheet(id: Int = 0) {
        viewModelScope.launch {
            _isSheetOpen.value = true
            _isSheetLoading.value = true
            _detail.value = getDetailUseCase(id)
            _isSheetLoading.value = false
        }
    }

    fun hideBottomSheet() {
        _isSheetOpen.value = false
    }
}