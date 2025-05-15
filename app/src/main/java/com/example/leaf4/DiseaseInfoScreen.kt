package com.example.leaf4

import androidx.compose.foundation.background
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import androidx.compose.material3.MaterialTheme



data class Disease(
    val name: String,
    val description: String,
    val imageUrl: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiseaseInfoScreen() {
//    var query by remember { mutableStateOf(TextFieldValue("")) }

    val allDiseases = listOf(
        Disease(
            "Apple Scab",
            "This fungal disease manifests as dark, velvety or sooty spots on the leaves and fruit of apple trees. These spots may start small and then enlarge, sometimes causing the leaves to become distorted or drop prematurely. On the fruit, the scabs can become corky and cracked, reducing the fruit's quality and marketability.",
            "https://fff.hort.purdue.edu/wp-content/uploads/2022/04/4-300x200.jpg"
        ),
        Disease(
            "Cherry Powdery Mildew",
            "This fungal disease appears as a white or grayish powdery coating on the upper surfaces of cherry leaves. In severe cases, it can also affect the undersides of leaves, young shoots, and even the fruit. The infected leaves may curl, become stunted, and eventually turn yellow or brown.",
            "https://i0.wp.com/blog.pestprophet.com/wp-content/uploads/2018/12/powdery-tcherry1_zoom.jpg?w=900&ssl=1"
        ),
        Disease(
            "Corn Cercospora Leaf Spot / Gray Leaf Spot",
            "This fungal disease on corn is characterized by tan to gray, rectangular lesions on the leaves. These lesions typically develop parallel to the leaf veins and can grow quite long. In severe infections, the lesions can coalesce, leading to significant leaf damage and reduced photosynthetic capacity.",
            "https://upload.wikimedia.org/wikipedia/commons/1/12/Gray_leaf_spot_Cercospora_zeae-maydis_5465607.png"
        ),
        Disease(
            "Corn Northern Leaf Blight",
            "This fungal disease presents as long, elliptical, grayish-tan lesions on corn leaves. These lesions typically start on the lower leaves and progress upwards. Under humid conditions, dark spores may be visible within the lesions. Severe infection can lead to premature leaf death.",
            "https://cropprotectionnetwork.org/image?s=%2Fimg%2Fhttp%2Fgeneral%2FNorthern-Corn-Leaf-Blight-Alison-Robertson-4.jpg%2Fd12eeb08a20a8d508df7919a0b4c7e49.jpg&h=256&w=316&fit=cover"        ),
        Disease(
            "Grape Esca (Black Measles)",
            "This complex fungal disease of grapevines shows various symptoms, including dark spots or streaks on the canes, discolored and brittle wood when cut open, and characteristic 'tiger-stripe' patterns on the leaves (yellow areas between the veins that eventually turn brown and necrotic). Berries may also shrivel and drop.",
            "https://source.roboflow.com/dMjGrME8SnaLLAZzZe5d/tBJQvVpGJVYXrsDQhyTn/original.jpg"
        ),
        Disease(
            "Grape Leaf Blight (Isariopsis Leaf Spot)",
            "This fungal disease is characterized by small, circular to irregular brown spots on grape leaves. These spots often have a lighter center and may be surrounded by a darker border. In humid conditions, the fungus may produce white, fuzzy growth on the undersides of the leaves within the spots. Severe infection can lead to defoliation.",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRdbOul0pnvUcL9iKGBrmAu54ShTH6K97zu1Q&s"        ),
        Disease(
            "Peach Bacterial Spot",
            "This bacterial disease affects the leaves, twigs, and fruit of peach trees. On leaves, it appears as small, dark, water-soaked spots that eventually become angular and may have a purplish-red halo. The centers of these spots often dry out and fall away, giving a 'shot-hole' appearance. Fruit lesions are small, pitted, and may crack.",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTO-m6OXH5CDNBYnL378GDznpCFYMXzGAs7rg&s"        ),
        Disease(
            "Pepper Bacterial Spot",
            "Similar to bacterial spot on peaches, this bacterial disease on bell peppers causes small, water-soaked spots on the leaves and fruit. These spots become brown and raised on the leaves and can lead to defoliation. On the fruit, the spots are raised, scab-like, and may have a rough texture.",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT30kt7zNFF6La6bET2Vf3k6ixZAxYm3DyR7Q&s"        ),
        Disease(
            "Potato Late Blight",
            "This destructive fungal disease of potatoes causes water-soaked lesions on the leaves and stems, often starting at the tips or edges. These lesions quickly enlarge and turn brown or black. Under cool, humid conditions, a white, cottony mold may be visible on the undersides of the leaves. The disease can rapidly spread and also affect the tubers, causing a reddish-brown rot.",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT59UZq2s60UL9MJwEcSN_GPXNlgsSpAsWM9w&s"        ),
        Disease(
            "Strawberry Leaf Scorch",
            "This fungal disease on strawberries is characterized by numerous small, dark purple to black spots on the upper surface of the leaves. Unlike leaf spot diseases with lighter centers, leaf scorch spots remain dark and may coalesce, leading to the entire leaf becoming purplish-red or brown and appearing 'scorched.'",
            "https://cdn.mos.cms.futurecdn.net/XkvoUQ5ffAU8j5Fq8DK3Vf.jpg"    ),
        Disease(
            "Tomato Late Blight",
            "This is the same destructive fungal disease that affects potatoes and also attacks tomato plants. It causes rapidly developing, water-soaked lesions on the leaves, often starting on older leaves. These lesions quickly turn brown and may have a fuzzy, white mold growth on the undersides, especially in humid conditions. The disease can also affect the stems and fruits, causing dark, greasy spots on the fruits that can lead to rot. Late blight can spread very quickly and be devastating to tomato crops.",
            "https://content.peat-cloud.com/w400/tomato-late-blight-tomato-1556463954.jpg"        )
    )


    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }

    val filteredDiseases = allDiseases.filter {
        it.name.contains(searchQuery.text, ignoreCase = true)
    }

    Scaffold(

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                placeholder = { Text("Search Disease...") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (filteredDiseases.isEmpty()) {
                Text(
                    text = "No results found",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                LazyColumn {
                    items(filteredDiseases) { disease ->
                        DiseaseCard(disease)
                    }
                }
            }
        }
    }
}
    // Filter diseases based on the query
//    val filteredDiseases = if (query.text.isEmpty()) {
//        diseases
//    } else {
//        diseases.filter {
//            it.name.contains(query.text, ignoreCase = true)
//        }
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        // Search Bar with magnifier icon
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(
//                onClick = {
//                    // Optionally, trigger a search here or clear the search
//                    // if you want functionality when the magnifier is clicked
//                }
//            ) {
//                Icon(Icons.Filled.Search, contentDescription = "Search")
//            }
//
//            BasicTextField(
//                value = query,
//                onValueChange = { query = it },
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(8.dp)
//                    .background(Color.Gray.copy(alpha = 0.1f), shape = RoundedCornerShape(8.dp))
//                    .padding(16.dp),
//                textStyle = MaterialTheme.typography.bodyMedium,
//                singleLine = true
//            )
//        }
//
//        // Show message if no results found
//        if (filteredDiseases.isEmpty()) {
//            Text(text = "No results found", style = MaterialTheme.typography.bodyMedium)
//        }
//
//        // List of diseases
//        LazyColumn(
//            modifier = Modifier.fillMaxSize()
//        ) {
//            items(filteredDiseases) { disease ->
//                DiseaseCard(disease)
//            }
//        }
//    }
//}

@Composable
fun DiseaseCard(disease: Disease) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = disease.name,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = disease.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Image for the disease
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(disease.imageUrl)
                    .crossfade(true)
                    .build(),
                contentDescription = "Sample Image of ${disease.name}",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.Crop
            )
        }
    }
}

