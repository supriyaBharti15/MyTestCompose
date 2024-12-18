package com.example.mytestcompose.view

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mytestcompose.R
import com.example.mytestcompose.model.Product
import com.example.mytestcompose.utils.Destination
import com.example.mytestcompose.utils.Progressbar
import com.example.mytestcompose.utils.StateController
import com.example.mytestcompose.viewModel.ProductDetailSharedViewModel
import com.example.mytestcompose.viewModel.ProductViewModel

@Composable
fun ProductScreen(navController: NavController, vm: ProductDetailSharedViewModel) {
    val viewModel:ProductViewModel= hiltViewModel()
    val state=viewModel.product.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getProduct()
    }
    when(val d=state.value){
        StateController.Empty -> {}
        is StateController.Failure -> {
            Log.d("Supriya",d.msg)
        }
        StateController.Loading -> {
            Progressbar()
        }
        is StateController.Success -> {
            LazyColumn(content = {
                items(d.apiData.products){
                    ProductItem(data = it,navController,vm)
                }
            })
        }
        else->{}
    }
}

@Composable
fun ProductItem(data: Product, navController: NavController, viewModel: ProductDetailSharedViewModel){
    Card (elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier.padding(12.dp)){
        Row(modifier = Modifier.clickable {
            viewModel.setData(data)
            navController.navigate(Destination.ITEM_DETAIL)  }) {
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.thumbnail)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.img),
                    contentDescription = "image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(width = 120.dp, height = 120.dp)
                )
            }
            Column {
                Text(text = data.title,
                    maxLines = 1,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp))
                Text(text = data.description,
                    maxLines = 1,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Thin,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp))
                Text(text = data.availabilityStatus,
                    maxLines = 1,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Thin,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp))
                Text(text = data.returnPolicy,
                    maxLines = 1,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Thin,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp))
            }
        }
    }

}
