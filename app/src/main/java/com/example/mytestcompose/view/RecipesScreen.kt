package com.example.mytestcompose.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mytestcompose.R
import com.example.mytestcompose.model.recipes.Recipe
import com.example.mytestcompose.utils.Destination
import com.example.mytestcompose.utils.Progressbar
import com.example.mytestcompose.utils.StateController
import com.example.mytestcompose.viewModel.RecipesDetailSharedViewModel
import com.example.mytestcompose.viewModel.RecipesViewModel

@Composable
fun RecipeScreen(navController: NavController,vm: RecipesDetailSharedViewModel) {
    val viewMode: RecipesViewModel = hiltViewModel()
    val dataState = viewMode.recipes.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewMode.getRecipes()
    }
    when (val data = dataState.value) {
        StateController.Empty -> {}
        is StateController.Failure -> {
            Log.d("Supriya", data.msg)
        }

        StateController.Loading -> {
            Progressbar()
        }

        is StateController.Success -> {

            LazyColumn(content = {
                items(data.apiData.recipes) {
                    Log.d("Supriya", it.cuisine)

                    RecipesList(it,navController,vm)
                }
            })
        }
    }

}

@Composable
fun RecipesList(list: Recipe, navController: NavController, vm: RecipesDetailSharedViewModel) {
    Card(
        modifier = Modifier.padding(16.dp).fillMaxWidth(),
        elevation = CardDefaults.cardElevation(15.dp)
    ) {
        Row(modifier = Modifier.clickable {
            vm.setDetail(list)
            navController.navigate(Destination.RECIPES_DETAIL_SCREEN)
        }) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(list.image)
                    .crossfade(true)
                    .build(),
                contentDescription = "image",
                placeholder = painterResource(id = R.drawable.img),
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width = 120.dp, height = 140.dp)
            )
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(start = 10.dp, bottom = 8.dp)) {
                Text(text = list.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W700,
                    style = MaterialTheme.typography.headlineMedium,
                    maxLines = 1)
                Text(text = "Cuisine: "+list.cuisine,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Thin,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1)

                Text(text = "Difficulty: "+list.difficulty,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Thin,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1)
                Text(text = "CookTimeMinutes: "+list.cookTimeMinutes ,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Thin,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1)
                Text(text = "MealType: "+list.mealType ,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Thin,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1)
            }
        }

    }
}