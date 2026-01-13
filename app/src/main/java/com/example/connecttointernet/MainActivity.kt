package com.example.connecttointernet

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.connecttointernet.ui.ConnectScreen
import com.example.connecttointernet.ui.theme.ConnectToInternetTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ConnectToInternetTheme {
                ConnectScreen()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewConnectScreen() {
    ConnectToInternetTheme {
        ConnectScreen()
    }
}