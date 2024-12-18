package com.example.mytestcompose.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.mytestcompose.R
import com.example.mytestcompose.utils.Destination
import com.example.mytestcompose.viewModel.UserPrefViewModel
import kotlin.math.sin

@Composable
fun LoginScreen(navController: NavController,viewModel: UserPrefViewModel) {
    val userID = viewModel.userID.collectAsState()
    val pass = viewModel.password.collectAsState()
    val userId = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Yellow)
    ) {
        Card(
            elevation = CardDefaults.cardElevation(20.dp),
            colors = CardDefaults.cardColors(Color.White),
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp)
                .align(Alignment.Center)
        ) {
            Column(
                modifier = Modifier.padding(
                    start = 20.dp,
                    end = 20.dp, top = 30.dp, bottom = 30.dp
                )
            ) {
                Text(
                    text = "Login  ",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W700, modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 30.dp)
                )
                OutlinedTextField(shape = RoundedCornerShape(10.dp),
                    value = userId.value, onValueChange = {
                        userId.value = it
                    },
                    label = { Text(text = " UserId") },
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    ),
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.userid),
                            contentDescription = "userID"
                        )
                    })
                val password = remember {
                    mutableStateOf("")
                }
                OutlinedTextField(shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(top = 20.dp),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        keyboardType = KeyboardType.Password
                    ),
                    value = password.value,
                    onValueChange = {
                        password.value = it
                    },
                    label = { Text(text = " Password") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = {
                        Image(
                            painter = painterResource(id = R.drawable.password),
                            contentDescription = "userID"
                        )
                    }
                )
                Button(
                    onClick = {
                        if (userId.value.isNotEmpty() && password.value.isNotEmpty()) {
                            if (userId.value == userID.value
                                && password.value == pass.value
                            ) {
                                viewModel.saveIsLogin(true)
                                navController.navigate(Destination.HOME_MENU){
                                    popUpTo(0)
                                }
                            } else {
                                Log.d("LOGIN", "Wrong UserID & password")
                            }
                        } else {
                            Log.d("LOGIN", "Empty userid and password")
                        }
                    },
                    modifier = Modifier
                        .padding(10.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Login")
                }

                TextButton(
                    onClick = { navController.navigate(Destination.SIGNUP_SCREEN) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Signup")

                }
            }

        }
    }
}