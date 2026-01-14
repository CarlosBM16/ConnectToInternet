package com.example.connecttointernet.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.connecttointernet.model.Image
import com.example.connecttointernet.network.PokeApi
import com.example.connecttointernet.network.PokeData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ConnectViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ConnectUiState())
    val uiState: StateFlow<ConnectUiState> = _uiState.asStateFlow()

    init {
        loadPokemons()
    }

    private fun loadPokemons() {
        viewModelScope.launch {
            try {
                val lista = mutableListOf<PokeData>()

                for (i in 1..151) {
                    val pokemon = PokeApi.retrofitService.getPokemon(i)
                    lista.add(pokemon)
                }

                _uiState.update { estado ->
                    estado.copy(one_generation = lista)
                }

            } catch (e: Exception) {
                Log.e("ERROR_API", e.message ?: "Error")
            }
        }
    }
}