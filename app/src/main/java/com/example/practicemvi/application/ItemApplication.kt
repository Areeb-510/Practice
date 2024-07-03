package com.example.practicemvi.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class ItemApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}