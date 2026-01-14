package com.example.connecttointernet

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.connecttointernet.ui.ConnectScreen
import com.example.connecttointernet.ui.ConnectViewModel
import kotlinx.coroutines.CoroutineScope

enum class Generations {
    GEN_1,
    GEN_2,
    GEN_3,
    GEN_4,
    GEN_5,
}
@Composable
fun Main(
    navController: NavHostController = rememberNavController(),
    viewModel: ConnectViewModel = viewModel()
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val uiState by viewModel.uiState.collectAsState()


    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(Modifier.height(12.dp))

                    Text("Selection Generation", modifier = Modifier.padding(16.dp), style = MaterialTheme.typography.titleLarge)

                    HorizontalDivider()

                    NavigationDrawerItem(
                        label = {
                            Text("1º Generation")
                        },
                        selected = false,
                        onClick = {
                            navController.navigate(Generations.GEN_1.name)
                        }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text("2º Generation")
                        },
                        selected = false,
                        onClick = {

                        }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text("3º Generation")
                        },
                        selected = false,
                        onClick = {

                        }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text("4º Generation")
                        },
                        selected = false,
                        onClick = {

                        }
                    )
                    NavigationDrawerItem(
                        label = {
                            Text("5º Generation")
                        },
                        selected = false,
                        onClick = {

                        }
                    )
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                MainTopBar("Generación 1")
            }
        ) {
            innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Generations.GEN_1.name,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Generations.GEN_1.name) {
                    ConnectScreen(pokemones = uiState.one_generation)
                }
                composable(Generations.GEN_2.name) {
                    ConnectScreen(pokemones = uiState.two_generation)
                }
                composable(Generations.GEN_3.name) {
                    ConnectScreen(pokemones = uiState.three_generation)
                }
                composable(Generations.GEN_4.name) {
                    ConnectScreen(pokemones = uiState.four_generation)
                }
                composable(Generations.GEN_5  .name) {
                    ConnectScreen(pokemones = uiState.five_generation)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(
    generacion: String
) {
    TopAppBar(
        title = {
            Text(
                text = generacion,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    )
}