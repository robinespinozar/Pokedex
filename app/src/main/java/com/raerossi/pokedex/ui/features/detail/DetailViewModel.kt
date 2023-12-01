package com.raerossi.pokedex.ui.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.pokedex.data.remote.model.PokemonDetail
import com.raerossi.pokedex.domain.usecases.GetDetailUseCase
import com.raerossi.pokedex.domain.usecases.IsFavoritePokemonUseCase
import com.raerossi.pokedex.domain.usecases.UpdateFavoritePokemonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailUseCase: GetDetailUseCase,
    private val updateFavoritePokemonUseCase: UpdateFavoritePokemonUseCase,
    private val isFavoritePokemonUseCase : IsFavoritePokemonUseCase
) : ViewModel() {

    private val _isSheetOpen = MutableLiveData<Boolean>()
    val isSheetOpen: LiveData<Boolean> = _isSheetOpen

    private val _detail = MutableLiveData<PokemonDetail>()
    val detail: LiveData<PokemonDetail> = _detail

    private val _isSheetLoading = MutableLiveData<Boolean>()
    val isSheetLoading: LiveData<Boolean> = _isSheetLoading

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun openBottomSheet(id: Int = 0) {
        viewModelScope.launch {
            _isSheetOpen.value = true
            _isSheetLoading.value = true
            _detail.value = getDetailUseCase(id)
            _isFavorite.value = isFavoritePokemonUseCase(id)
            _isSheetLoading.value = false
        }
    }

    fun hideBottomSheet() {
        _isSheetOpen.value = false
    }

    fun updateFavoritePokemon(isFavorite: Boolean, id: Int) {
        viewModelScope.launch {
            _isFavorite.value = isFavorite
            updateFavoritePokemonUseCase(isFavorite, id)
        }
    }
}