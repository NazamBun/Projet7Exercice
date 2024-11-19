package com.openclassrooms.arista

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application() {
    companion object {
        const val USER_ID : Long = 1
    }
}
