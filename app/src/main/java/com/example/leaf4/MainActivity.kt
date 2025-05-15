package com.example.leaf4

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.leaf4.ui.theme.Leaf4Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            var imageUri: Uri? by remember { mutableStateOf(null) }
            var resultText by remember { mutableStateOf("") }
            var diseaseName by remember { mutableStateOf("") }
            var diseaseImage by remember { mutableStateOf("") }
            var diseaseDescription by remember { mutableStateOf("") }

            val pickImageLauncher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.GetContent()
            ) { uri ->
                imageUri = uri
            }

            Leaf4Theme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(
                                    text = when (currentRoute) {
                                        "home" -> "Leaf Disease Detector"
                                        "treatment" -> "Treatment Guide"
                                        "diseaseInfo" -> "Disease Information"
                                        "datasetInfo" -> "Dataset Information"
                                        else -> "Leaf Disease Detector"
                                    },
                                    style = MaterialTheme.typography.titleLarge
                                )
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                            ),
                            navigationIcon = {
                                if (currentRoute != "home") {

                                        IconButton(onClick = { navController.navigateUp() }) {
                                            Icon(Icons.Default.ArrowBack, "Back")
                                        }

                                } else null
                            }
                        )
                    },
                    bottomBar = {
                        BottomNavigationBar(navController, currentRoute)
                    }
                ) { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = "home",
                        modifier = Modifier.padding(padding)
                    ) {
                        composable("home") {
                            LeafDiseaseDetectionScreen(
                                imageUri = imageUri,
                                onPickImage = { pickImageLauncher.launch("image/*") },
                                onDetectDisease = {
                                    resultText = "Prediction pending..."
                                    diseaseName = "Leaf Blight"
                                    diseaseImage = "image_url"
                                    diseaseDescription = "Description of the leaf blight disease."
                                    resultText = "Disease detected!"
                                },
                                resultText = resultText,
                                diseaseName = diseaseName,
                                diseaseImage = diseaseImage,
                                diseaseDescription = diseaseDescription
                            )
                        }
                        composable("treatment") {
                            TreatmentScreen(diseaseName = diseaseName)
                        }
                        composable("diseaseInfo") {
                            DiseaseInfoScreen()
                        }
                        composable("datasetInfo") {
                            DatasetInfoScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, currentRoute: String?) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 8.dp
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = currentRoute == "home",
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.List, contentDescription = "Disease Info") },
            label = { Text("Diseases") },
            selected = currentRoute == "diseaseInfo",
            onClick = { navController.navigate("diseaseInfo") }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.Info, contentDescription = "Dataset Info") },
            label = { Text("Dataset") },
            selected = currentRoute == "datasetInfo",
            onClick = { navController.navigate("datasetInfo") }
        )
    }
}
