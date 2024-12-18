package com.example.mytestcompose.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import javax.inject.Inject

@HiltViewModel
class LaunchScreenViewModel @Inject constructor() :ViewModel() {
    private val _loginStatus= MutableLiveData<String>()
    var loginStatus: LiveData<String> = _loginStatus
    fun setLoginStatus(isLogin:Boolean){
        viewModelScope.launch {
            if(isLogin){
                _loginStatus.value="HomeMenu"
            }else{
                _loginStatus.value="LoginScreen"

            }
        }
    }
}