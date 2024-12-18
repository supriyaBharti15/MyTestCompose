package com.example.mytestcompose.utils

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferenceUtils @Inject constructor(private val sharedPref: SharedPreferences) {
    /*save string data in sp*/
    fun saveString(key:String,value:String){
        val editor:SharedPreferences.Editor=sharedPref.edit()
        editor.putString(key,value)
        editor.apply()
    }

    /*save int data*/
    fun saveInt(key:String,value:Int){
        val editor:SharedPreferences.Editor=sharedPref.edit()
        editor.putInt(key,value)
        editor.apply()
    }

    /*get saved String*/
    fun getString(key:String): String? {
        return sharedPref.getString(key,"")
    }

    /*get Int */
    fun getInt(key: String):Int{
        return sharedPref.getInt(key,0)
    }

    /*to clear all data*/
    fun clearSharedPreference(){
        val editor:SharedPreferences.Editor=sharedPref.edit()
        editor.clear()
        editor.apply()
    }

    /*delete specific data from SP*/
fun deleteUser(key:String){
    val editor:SharedPreferences.Editor=sharedPref.edit()
        editor.remove(key)
        editor.apply()
}
}