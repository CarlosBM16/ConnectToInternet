package com.example.connecttointernet.ui.theme

import androidx.lifecycle.ViewModel
import com.example.connecttointernet.ui.ConnectUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ConnectViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(ConnectUiState())
    val uiState : StateFlow<ConnectUiState> = _uiState.asStateFlow()
}