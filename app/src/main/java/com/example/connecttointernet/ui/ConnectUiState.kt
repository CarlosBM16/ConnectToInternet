package com.example.connecttointernet.ui

import com.example.connecttointernet.model.Image
import com.example.connecttointernet.network.PokeData

data class TestUiState (
    var image : Image = Image(),
    val tipo: String = "",
    val nombre: String = ""
)

data class ConnectUiState (
    val one_generation: List<PokeData> = emptyList(),
    val two_generation: List<PokeData> = emptyList(),
    val three_generation: List<PokeData> = emptyList(),
    val four_generation: List<PokeData> = emptyList(),
    val five_generation: List<PokeData> = emptyList(),
)