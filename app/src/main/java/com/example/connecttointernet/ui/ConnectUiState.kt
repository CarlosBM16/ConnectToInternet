package com.example.connecttointernet.ui

import com.example.connecttointernet.network.PokeData

data class ConnectUiState (
    val oneGeneration: List<PokeData> = emptyList(),
    val twoGeneration: List<PokeData> = emptyList(),
    val threeGeneration: List<PokeData> = emptyList(),
    val fourGeneration: List<PokeData> = emptyList(),
    val fiveGeneration: List<PokeData> = emptyList(),
)