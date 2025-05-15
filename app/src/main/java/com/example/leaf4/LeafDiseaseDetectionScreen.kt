package com.example.leaf4

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun LeafDiseaseDetectionScreen(
    imageUri: Uri?,
    onPickImage: () -> Unit,
    onDetectDisease: () -> Unit,
    resultText: String,
    diseaseName: String,
    diseaseImage: String,
    diseaseDescription: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Image preview
        if (imageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(imageUri),
                contentDescription = "Selected Leaf",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .shadow(4.dp),
                contentScale = ContentScale.Crop
            )
        } else {
            Text("No image selected", fontSize = 16.sp)
        }

        // Buttons to pick image and detect disease
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ElevatedButton(onClick = onPickImage) {
                Text("Pick Image")
            }
            ElevatedButton(onClick = onDetectDisease, enabled = imageUri != null) {
                Text("Detect Disease")
            }
        }

        // Result Section
        if (resultText == "Prediction pending...") {
            CircularProgressIndicator()
        } else if (resultText.isNotEmpty()) {
            Card(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Disease Detected: $diseaseName",
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Disease image
                    Image(
                        painter = rememberAsyncImagePainter(diseaseImage),
                        contentDescription = "Disease Image",
                        modifier = Modifier
                            .height(200.dp)
                            .clip(RoundedCornerShape(8.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    // Disease description
                    Text(
                        text = diseaseDescription,
                        fontSize = 16.sp
                    )

                    // Navigation to treatment screen
                    Spacer(modifier = Modifier.height(16.dp))
                    ElevatedButton(
                        onClick = {
                            // Navigate to Treatment screen
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("View Treatment")
                    }
                }
            }
        }
    }
}