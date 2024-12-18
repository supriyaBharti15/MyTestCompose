package com.example.mytestcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestcompose.model.quotes.QuotesResponse
import com.example.mytestcompose.repository.QuotesRepository
import com.example.mytestcompose.utils.StateController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(private val repository: QuotesRepository) : ViewModel() {
    private val _quotes = MutableStateFlow<StateController<QuotesResponse>>(StateController.Empty)
    val quotes: StateFlow<StateController<QuotesResponse>> = _quotes

    fun getQuotes() {
        viewModelScope.launch {
            _quotes.value = StateController.Loading
            try {
                repository.getQuotes().collect {
                    _quotes.value = StateController.Success(it)
                }
            } catch (e: Exception) {
                _quotes.value = StateController.Failure(e.message.toString())
            }
        }
    }
}