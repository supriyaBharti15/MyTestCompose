package com.example.mytestcompose.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mytestcompose.model.local_pref.UserPref
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserPrefViewModel @Inject constructor(private val userPref: UserPref) : ViewModel() {

    val userID = userPref.getUSerId()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = ""
        )
    val password = userPref.getPassword()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = ""
        )
val isLogin=userPref.getIsLogin()
    .stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = false
    )

    fun saveUserId(userId: String) {
        viewModelScope.launch {
            userPref.saveUSerId(userId)
        }
    }
    fun savePassword(password:String){
        viewModelScope.launch {
            userPref.savePassword(password)
        }
    }

    fun saveIsLogin(isLogin:Boolean){
        viewModelScope.launch {
            userPref.saveIsLogin(isLogin)
        }
    }
}