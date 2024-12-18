package com.example.mytestcompose.viewModel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mytestcompose.model.Product
import javax.inject.Inject

class ProductDetailSharedViewModel @Inject constructor():ViewModel() {
    private val _product=MutableLiveData<Product>()
    val product:LiveData<Product>
        get() = _product

    fun setData(data:Product){
        _product.value=data
    }

}