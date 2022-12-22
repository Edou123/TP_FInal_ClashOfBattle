package com.example.tp_final_clashofbattle

import android.app.Application
import com.example.tp_final_clashofbattle.Database.AppDatabase

class MainApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        AppDatabase.init(this)
    }
}