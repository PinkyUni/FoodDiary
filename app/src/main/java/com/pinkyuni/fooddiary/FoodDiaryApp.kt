package com.pinkyuni.fooddiary

import android.app.Application
import com.pinkyuni.fooddiary.di.diModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FoodDiaryApp: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FoodDiaryApp)
            modules(diModules)
        }
    }

}