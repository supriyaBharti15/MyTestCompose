package com.example.mytestcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mytestcompose.utils.Destination
import com.example.mytestcompose.view.AppToolBar
import com.example.mytestcompose.view.HomeScreen
import com.example.mytestcompose.view.ItemDetailScreen
import com.example.mytestcompose.view.LoginScreen
import com.example.mytestcompose.view.PhotoScreen
import com.example.mytestcompose.view.ProductDetailScreen
import com.example.mytestcompose.view.ProductScreen
import com.example.mytestcompose.view.QuotesScreen
import com.example.mytestcompose.view.RecipeScreen
import com.example.mytestcompose.view.RecipesDetailScreen
import com.example.mytestcompose.view.SignupScreen
import com.example.mytestcompose.view.TodoScreen
import com.example.mytestcompose.view.mackup.MessageBar
import com.example.mytestcompose.view.mackup.StateHosting
import com.example.mytestcompose.viewModel.LaunchScreenViewModel
import com.example.mytestcompose.viewModel.ProductDetailSharedViewModel
import com.example.mytestcompose.viewModel.RecipesDetailSharedViewModel
import com.example.mytestcompose.viewModel.UserPrefViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val userPrefViewModel: UserPrefViewModel by viewModels()
    private val launchViewModel: LaunchScreenViewModel by viewModels()
    lateinit var launch: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            userPrefViewModel.isLogin.collect {
                launch = if (it){
                    Destination.HOME_MENU
                } else{
                    Destination.LOGIN_SCREEN
                }
            }
        }
        setContent {
            //AppToolBar("TestCompose",launch)
             AppNavigation(launch,userPrefViewModel)
        }
    }
}

@Composable
fun AppNavigation(launch: String,vm: UserPrefViewModel) {
    val count = remember {
        mutableStateOf(0)
    }
    val navController = rememberNavController()
    val viewModel: ProductDetailSharedViewModel = viewModel()
    //val userPrefViewModel: UserPrefViewModel = hiltViewModel()

    val recSharedviewModel: RecipesDetailSharedViewModel = viewModel()
    NavHost(navController = navController, startDestination = launch) {
        composable(route = Destination.HOME_MENU) {
            HomeScreen(navController)
        }
        composable(route = Destination.LOGIN_SCREEN) {
            LoginScreen(navController, vm)
        }
        composable(route = Destination.SIGNUP_SCREEN) {
            SignupScreen(navController, vm)
        }
        composable(route = Destination.TODO_SCREEN) {
            TodoScreen()
        }
        composable(route = Destination.PRODUCT_SCREEN) {
            ProductScreen(navController, viewModel)
        }
        composable(route = Destination.COMMENT_SCREEN) {
            ProductDetailScreen()
        }
        composable(route = Destination.ITEM_DETAIL) {
            ItemDetailScreen(viewModel = viewModel)
        }
        composable(route = Destination.RECIPES_SCREEN) {
            RecipeScreen(navController, recSharedviewModel)
        }
        composable(route = Destination.PHOTO_SCREEN) {
            PhotoScreen()
        }
        composable(route = Destination.RECIPES_DETAIL_SCREEN) {
            RecipesDetailScreen(recSharedviewModel)
        }
        composable(route = Destination.QUOTES_SCREEN) {
            QuotesScreen()
        }
        composable(route = Destination.STATE_HOSTING_SCREEN) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize(1f)
            ) {
                StateHosting(count.value) { count.value++ }
                MessageBar(count.value)
            }

        }
    }
}
