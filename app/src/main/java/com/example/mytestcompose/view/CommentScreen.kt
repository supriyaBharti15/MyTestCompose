package com.example.mytestcompose.view

import android.os.Message
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mytestcompose.R
import com.example.mytestcompose.model.CommentResponseItem
import com.example.mytestcompose.model.Product
import com.example.mytestcompose.model.TodoResponseItem
import com.example.mytestcompose.utils.Progressbar
import com.example.mytestcompose.utils.StateController
import com.example.mytestcompose.viewModel.CommentViewModel
import com.example.mytestcompose.viewModel.TodoViewModel

@Preview
@Composable
fun ProductDetailScreen() {
    val viewModel: CommentViewModel = hiltViewModel()
    val dataState= viewModel.comment.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.getComment()
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
                    MessageCard(data = it)
                }
            })
        }
    }
}

@Composable
fun MessageCard(data: CommentResponseItem) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.sup),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colorScheme.primary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        var isExpanded by remember { mutableStateOf(false) }
        Column {
            Text(
                text = data.name,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 20.sp,
                fontWeight = FontWeight.W700
            )
            Text(text = data.email,
                fontSize = 18.sp,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Thin)

            Spacer(modifier = Modifier.height(4.dp))

            Surface( modifier = Modifier.clickable { isExpanded = !isExpanded },
                shape = MaterialTheme.shapes.medium,
                shadowElevation = 1.dp,
            ) {
                Text(
                    text = data.body,
                    modifier = Modifier.padding(all = 4.dp),
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                    style = MaterialTheme.typography.bodyMedium,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Thin
                )
            }
        }
    }
}