package com.example.mytestcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestcompose.model.TodoResponseItem
import com.example.mytestcompose.repository.TodoRepository
import com.example.mytestcompose.utils.StateController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val repository: TodoRepository) : ViewModel() {
    private val _todo =
        MutableStateFlow<StateController<List<TodoResponseItem>>>(StateController.Empty)
    val todo: StateFlow<StateController<List<TodoResponseItem>>>
        get() = _todo

    fun getTodo() {
        _todo.value = StateController.Loading
        viewModelScope.launch {
            try {
                repository.getTodo().collect {
                    _todo.value = StateController.Success(it)
                }
            } catch (e: Exception) {
                _todo.value = StateController.Failure(e.message.toString())
            }
        }
    }
}