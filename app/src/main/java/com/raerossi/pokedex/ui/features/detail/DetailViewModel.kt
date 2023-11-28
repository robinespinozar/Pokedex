package com.raerossi.pokedex.ui.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.raerossi.pokedex.data.remote.model.PokemonDetail
import com.raerossi.pokedex.domain.usecases.GetDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel
@Inject constructor(private val getDetailUseCase: GetDetailUseCase) : ViewModel() {

    private val _isSheetOpen = MutableLiveData<Boolean>()
    val isSheetOpen: LiveData<Boolean> = _isSheetOpen

    private val _detail = MutableLiveData<PokemonDetail>()
    val detail: LiveData<PokemonDetail> = _detail

    private val _isSheetLoading = MutableLiveData<Boolean>()
    val isSheetLoading: LiveData<Boolean> = _isSheetLoading

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