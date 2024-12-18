package com.example.mytestcompose.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.mytestcompose.R
import com.example.mytestcompose.model.Product
import com.example.mytestcompose.view.mackup.ImageSlider
import com.example.mytestcompose.viewModel.ProductDetailSharedViewModel
import kotlin.coroutines.Continuation

@Composable
fun ItemDetailScreen( viewModel: ProductDetailSharedViewModel) {
    val data = viewModel.product.value
    Column(modifier = Modifier.padding(all = 20.dp)) {
        ImageSlider(modifier = Modifier,data!!.images)
        /*AsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data(data?.thumbnail)
            .crossfade(true)
            .build(),
            placeholder = painterResource(id = R.drawable.img),
            contentDescription = "img",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(width = 400.dp, height = 400.dp)
        )*/

        Spacer(modifier = Modifier.height(height = 10.dp))
        Text(text = data!!.description,
            fontSize = 18.sp,
            fontWeight = FontWeight.Thin,
            style = MaterialTheme.typography.bodyMedium)

        Text(text = data.availabilityStatus,
            fontSize = 18.sp,
            fontWeight = FontWeight.W400,
            style = MaterialTheme.typography.bodyMedium)

        Text(text = data.warrantyInformation,
            fontSize = 18.sp,
            fontWeight = FontWeight.Thin,
            style = MaterialTheme.typography.bodyMedium)
    }
}

