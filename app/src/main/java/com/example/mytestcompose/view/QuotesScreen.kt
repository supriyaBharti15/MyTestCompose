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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytestcompose.model.quotes.Quote
import com.example.mytestcompose.model.quotes.QuotesResponse
import com.example.mytestcompose.utils.Progressbar
import com.example.mytestcompose.utils.StateController
import com.example.mytestcompose.viewModel.QuotesViewModel

@Composable
fun QuotesScreen() {
    val viewModel:QuotesViewModel= hiltViewModel()
    val data=viewModel.quotes.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getQuotes()
    }
    when(val response=data.value){
        StateController.Empty -> {}
        is StateController.Failure -> {
            Log.d("TAG",response.msg)
        }
        StateController.Loading -> {
            Progressbar()
        }
        is StateController.Success -> {
            LazyColumn(content = {
                items(response.apiData.quotes){
                    QuotesListItem(quotes = it)
                }
            })
        }
    }
}

@Composable
fun QuotesListItem(quotes: Quote) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.cardElevation(16.dp)
    )
    {
        Row {
            Column (modifier = Modifier.padding(15.dp)){
                Text(
                    text = quotes.quote,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Black,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp))
                Text(text = quotes.author,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Thin,
                    style = MaterialTheme.typography.bodyMedium)
            }
            
        }
    }

}