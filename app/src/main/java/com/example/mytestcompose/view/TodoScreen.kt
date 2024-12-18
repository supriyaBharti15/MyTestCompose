package com.example.mytestcompose.view

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytestcompose.model.TodoResponseItem
import com.example.mytestcompose.utils.Progressbar
import com.example.mytestcompose.utils.StateController
import com.example.mytestcompose.viewModel.TodoViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@Composable
fun TodoScreen() {
    val viewModel:TodoViewModel= hiltViewModel()
    val dataState= viewModel.todo.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getTodo()
    }
    when(val d=dataState.value){
        StateController.Empty -> {}
        is StateController.Failure -> {
            Log.d("TAG",d.msg)
        }
        StateController.Loading -> {
            Progressbar()
        }
        is StateController.Success -> {
            Log.d("TAG",d.apiData.toString())
            LazyColumn (content = {
                items(d.apiData){
                    ToDoListItem(data = it)
                }
            })
        }
    }
}

@Composable
fun ToDoListItem(data:TodoResponseItem){
    Card(modifier = Modifier.padding(16.dp)
        .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(10.dp)) {
        Row {
            Column {
                Text(text = data.id,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W700,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(10.dp))
                Text(text = data.userId,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.W400,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp))
                Text(text = data.title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Thin,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 10.dp, bottom = 10.dp))
            }
        }
    }
}