package com.example.mytestcompose.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.mytestcompose.viewModel.HomeMenuViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: HomeMenuViewModel = hiltViewModel()
    val data = viewModel.getData()
    LazyVerticalGrid(columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.SpaceAround
    )
    {
        items(data){
            HomeList(name = it,navController)
        }

    }
}

@Composable
fun HomeList(name: String,navController: NavController) {
    Card(
        elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier.padding(10.dp)
    ) {
        Column(modifier = Modifier.size(width = 200.dp, height = 200.dp)
            .clickable { navController.navigate(name) },
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = name,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineLarge,
                maxLines = 1,
                fontWeight = FontWeight.Medium
            )
        }
    }
}