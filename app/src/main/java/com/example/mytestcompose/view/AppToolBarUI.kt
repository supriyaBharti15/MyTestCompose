package com.example.mytestcompose.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.mytestcompose.AppNavigation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppToolBar(name: String, launch: String){
println("=========== sup  $launch")
    Scaffold(topBar = {
        TopAppBar(title = { Text(text = name, color = Color.Black) },
            modifier = Modifier.background(Color.Blue))
    }){
        Box (modifier = Modifier.padding(it)){
            //AppNavigation()
        }
    }
}