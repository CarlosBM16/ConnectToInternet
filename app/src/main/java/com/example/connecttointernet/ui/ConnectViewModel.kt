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

    init {
        loadPokemons()
    }

    private fun loadPokemons() {
        viewModelScope.launch {
            try {

                val one_generation = mutableListOf<PokeData>()
                val two_generation = mutableListOf<PokeData>()
                val three_generation = mutableListOf<PokeData>()
                val four_generation = mutableListOf<PokeData>()
                val five_generation = mutableListOf<PokeData>()

                for (i in 1..1025) {
                    val pokemon = PokeApi.retrofitService.getPokemon(i)
                    if (i <= 151) {
                        one_generation.add(pokemon)
                    } else if (i <= 251) {
                        two_generation.add(pokemon)
                    } else if (i <= 251) {
                        three_generation.add(pokemon)
                    } else if (i <= 251) {
                        four_generation.add(pokemon)
                    } else if (i <= 251) {
                        five_generation.add(pokemon)
                    }

                }

                _uiState.update { estado ->
                    estado.copy(one_generation = one_generation)
                    estado.copy(two_generation = two_generation)
                    estado.copy(three_generation = three_generation)
                    estado.copy(four_generation = four_generation)
                    estado.copy(five_generation = five_generation)
                }

            } catch (e: Exception) {
                Log.e("ERROR_API", e.message ?: "Error")
            }
        }
    }
}