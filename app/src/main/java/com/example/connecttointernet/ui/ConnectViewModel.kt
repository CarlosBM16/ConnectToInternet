package com.example.connecttointernet.ui

import androidx.lifecycle.ViewModel
import com.example.connecttointernet.model.Image
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConnectViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ConnectUiState())
    val uiState: StateFlow<ConnectUiState> = _uiState.asStateFlow()

    init {
        getRemoteImage()
    }

    fun getRemoteImage() {
        val image : Image = Image(
            url = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/132.png",
            description = "Ditto image")

        _uiState.value.image = image
    }

}