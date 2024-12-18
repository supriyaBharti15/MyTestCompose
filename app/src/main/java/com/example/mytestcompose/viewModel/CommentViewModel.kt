package com.example.mytestcompose.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestcompose.model.CommentResponseItem
import com.example.mytestcompose.repository.CommentRepository
import com.example.mytestcompose.utils.StateController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CommentViewModel @Inject constructor(private val repository: CommentRepository) :
    ViewModel() {
    private val _comment =
        MutableStateFlow<StateController<List<CommentResponseItem>>>(StateController.Empty)
    val comment: StateFlow<StateController<List<CommentResponseItem>>>
        get() = _comment

    fun getComment() {
        _comment.value = StateController.Loading
        viewModelScope.launch {
            try {
                repository.getComment().collect {
                    _comment.value = StateController.Success(it)
                }
            } catch (e: Exception) {
                _comment.value = StateController.Failure(e.message.toString())
            }
        }
    }
}