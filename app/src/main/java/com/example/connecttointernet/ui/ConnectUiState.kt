package com.example.connecttointernet.ui

import com.example.connecttointernet.model.Image

data class ConnectUiState (
    var image : Image = Image(),
    val tipo: String = "",
    val nombre: String = ""
)