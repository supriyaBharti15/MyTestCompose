package com.example.mytestcompose.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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

class Destination {
    companion object{
        const val HOME_MENU:String="HomeMenu"
        const val LOGIN_SCREEN:String="Login"
        const val SIGNUP_SCREEN:String="Signup"
        const val TODO_SCREEN:String="Todo"
        const val PRODUCT_SCREEN:String="Product"
        const val COMMENT_SCREEN:String="Comment"
        const val ITEM_DETAIL:String="ItemDetail"
        const val RECIPES_SCREEN:String="Recipes"
        const val PHOTO_SCREEN:String="Photo"
        const val RECIPES_DETAIL_SCREEN:String="RecipesDetail"
        const val QUOTES_SCREEN:String="Quotes"
        const val STATE_HOSTING_SCREEN:String="StateHosting"
    }
}
