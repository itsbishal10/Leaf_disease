package com.example.leaf4

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TreatmentScreen(diseaseName: String) {
    // Treatment steps are hypothetical and should be replaced with actual treatment info
    Column(modifier = Modifier.padding(16.dp)) {
        Text("Treatment for $diseaseName", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // Example treatment steps
        Text("Step 1: Apply fungicide to control fungal growth.", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Step 2: Prune and remove infected leaves to prevent further spread.", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Step 3: Ensure proper air circulation around the plants to avoid high humidity.", fontSize = 16.sp)
        Spacer(modifier = Modifier.height(8.dp))

        Text("Step 4: Use resistant plant varieties where available.", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(16.dp))

        // Optional: A button to view more detailed treatment suggestions (like a link to a website)
        Button(onClick = { /* Navigate to detailed treatment information */ }) {
            Text("Get More Treatment Info")
        }
    }
}


