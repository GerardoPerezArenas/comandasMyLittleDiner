package com.gerardo.comandas

import android.app.Application
import com.gerardo.comandas.di.AppContainer

class ComandasApplication : Application() {
    
    override fun onCreate() {
        super.onCreate()
        AppContainer.initialize(this)
    }
}