package com.example.leaf4



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DatasetInfoScreen() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Dataset Information", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Dataset source
        Text("Source: Kaggle - Plant Disease Dataset", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        // Number of images in the dataset
        Text("Number of Images: 46,159", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        // Number of classes in the dataset
        Text("Number of Classes: 20", fontSize = 18.sp)

        Spacer(modifier = Modifier.height(8.dp))

        // Dataset description (optional)
        Text(
            text = "This dataset contains images of various plant diseases across different categories. It is used to train models for detecting diseases on plant leaves.",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Example for dataset stats or visualizations (if available)
        Text("Additional Dataset Stats will be added here.", fontSize = 16.sp)
    }
}
