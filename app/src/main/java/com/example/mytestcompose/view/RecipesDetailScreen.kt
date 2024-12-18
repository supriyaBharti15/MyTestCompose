package com.example.mytestcompose.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mytestcompose.R
import com.example.mytestcompose.viewModel.RecipesDetailSharedViewModel

@Composable
fun RecipesDetailScreen(viewModel: RecipesDetailSharedViewModel) {
    val data = viewModel.detail.value
    val verticalScroll = rememberScrollState()

    Box {
        Column(Modifier.verticalScroll(verticalScroll)) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(data!!.image)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.img),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 400.dp, height = 400.dp),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalDivider(color = Color.Black, thickness = 1.dp)
            Column(modifier = Modifier.padding(start = 26.dp)) {
                Text(
                    text = "Instructions:",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    style = MaterialTheme.typography.headlineMedium
                )
                InstructionsList(data.instructions)
                Text(
                    text = "Ingredients",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 15.dp)
                )


                Column(verticalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = "" + data.ingredients,
                        fontSize = 16.sp
                    )
                }
                Text(
                    text = "Ratings:${data.rating}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 15.dp)
                )
                StarRatingBar(5, data.rating)
            }

        }
    }
}

@Composable
fun InstructionsList(instructions: List<String>) {
    Column {
        for (i in 0 until instructions.size) {
            Text(
                text = "Step:: ${i + 1} ${instructions[i]}",
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}
