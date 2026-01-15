package com.example.connecttointernet.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    val oneGeneration = mutableListOf<PokeData>()
    val twoGeneration = mutableListOf<PokeData>()
    val threeGeneration = mutableListOf<PokeData>()
    val fourGeneration = mutableListOf<PokeData>()
    val fiveGeneration = mutableListOf<PokeData>()

    init {
        loadPokemons()
    }

    private fun loadPokemons() {
        viewModelScope.launch {
            try {

                setPokemonxGeneretion()

                _uiState.update { estado ->
                    estado.copy(
                        oneGeneration = oneGeneration,
                        twoGeneration = twoGeneration,
                        threeGeneration = threeGeneration,
                        fourGeneration = fourGeneration,
                        fiveGeneration = fiveGeneration
                    )
                }

            } catch (e: Exception) {
                Log.e("ERROR_API", e.message ?: "Error")
            }
        }
    }

    private suspend fun setPokemonxGeneretion() {
        for (i in 1..649) {
            val pokemon = PokeApi.retrofitService.getPokemon(i)
            if (i <= 151) {
                oneGeneration.add(pokemon)
            } else if (i <= 251) {
                twoGeneration.add(pokemon)
            } else if (i <= 386) {
                threeGeneration.add(pokemon)
            } else if (i <= 493) {
                fourGeneration.add(pokemon)
            } else {
                fiveGeneration.add(pokemon)
            }
        }
    }
}