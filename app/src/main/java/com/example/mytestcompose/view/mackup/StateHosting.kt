package com.example.mytestcompose.view.mackup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StateHosting(count:Int,increment:()->Unit) {

    Column (verticalArrangement = Arrangement.Center){
        Text(text = "count is= ${count}", fontSize = 30.sp)
        Button(onClick = {
            increment()
        }) {
            Text(text = "send notification")
        }
    }

}

@Composable
fun MessageBar(count:Int) {
    Card(elevation = CardDefaults.cardElevation(10.dp),
        modifier = Modifier.padding(20.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(imageVector = Icons.Outlined.Favorite, contentDescription = "image")
            Text(text = "Message sent so far: $count")
        }
    }
}