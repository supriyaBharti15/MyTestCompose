package com.example.mytestcompose.hilt

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.qualifiers.ApplicationContext

@HiltAndroidApp
class HiltApplication:Application() {
}