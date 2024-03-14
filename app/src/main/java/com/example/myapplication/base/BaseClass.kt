package com.example.myapplication.base

import android.app.Application
import com.akter.testlibrary.Adfinix
import com.akter.testlibrary.utils.AdfinixAdType

class BaseClass : Application() {
    override fun onCreate() {
        super.onCreate()
        // adfinix setup
        Adfinix.initialize(36,AdfinixAdType.TEST)
    }
}