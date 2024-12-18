package com.example.mytestcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestcompose.model.ProductResponse
import com.example.mytestcompose.repository.ProductRepository
import com.example.mytestcompose.utils.StateController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: ProductRepository) :
    ViewModel() {
    private val _product = MutableStateFlow<StateController<ProductResponse>>(StateController.Empty)
    val product: StateFlow<StateController<ProductResponse>>
        get() = _product

    fun getProduct() {
        viewModelScope.launch {
            _product.value = StateController.Loading
            try {
                repository.getProduct(0, 10).collect {
                    _product.value = StateController.Success(it)
                }
            } catch (e: Exception) {
                _product.value = StateController.Failure(e.message.toString())
            }
        }
    }
}