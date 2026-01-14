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
import kotlin.random.Random

class ConnectViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ConnectUiState())
    val uiState: StateFlow<ConnectUiState> = _uiState.asStateFlow()

    init {
        getRemoteImage()
    }

    fun getRemoteImage() {
        viewModelScope.launch {
            try {
                val idPokemon = (1..1000).random()
                val pokeInfo = PokeApi.retrofitService.getPokemon(idPokemon)

                //Unimos mediante comas los tipos del pokemeno que obtenemos de pokeInfo
                val tipos = pokeInfo.types.joinToString(", "){ it.type.name }

                val urlImagen = pokeInfo.sprites.other.officialArtwork.url

                _uiState.update{

                        estado ->
                    estado.copy(
                        nombre = pokeInfo.name,
                        tipo = tipos,
                        image = Image(
                            url = urlImagen,
                            description = pokeInfo.name
                        )
                    )
                }

            }catch (e: Exception){
                Log.e("ERROR_API", "Fallo con la conexion: ${e.message}")
            }
        }

    }

}