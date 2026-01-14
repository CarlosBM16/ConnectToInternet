package com.example.connecttointernet.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ConnectScreen(
    connectViewModel: ConnectViewModel = viewModel()
) {
    val connectUiState by connectViewModel.uiState.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(64.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "\uD83C\uDFB5POKEMON HAZTE CON TOOODOOOS\uD83C\uDFB5",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.width(100.dp))

            Text(
                text = connectUiState.nombre,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            Spacer(modifier = Modifier.width(20.dp))

            Text(
                text = connectUiState.tipo,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )

            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = connectUiState.image.url,
                contentDescription = connectUiState.image.description
            )

            Spacer(modifier = Modifier.width(10.dp))

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewConnectScreen() {
    ConnectScreen()
}