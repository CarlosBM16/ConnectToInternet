package com.example.connecttointernet.network

data class PokeData(
    val name: String,
    val id: Int,
    val types: List<TypeEntry>,
    val cries: PokemonCries,
    val sprites: PokemonSprites
)
