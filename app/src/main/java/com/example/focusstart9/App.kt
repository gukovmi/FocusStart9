package com.example.focusstart9

import android.app.Application
import android.content.ContentResolver
import android.content.Context
import androidx.room.Room
import com.example.focusstart9.di.AppComponent
import com.example.focusstart9.di.DaggerAppComponent
import com.example.focusstart9.domain.db.AppDatabase

class App : Application() {

    companion object {
        lateinit var instance: App
        lateinit var db: AppDatabase
        lateinit var resolver: ContentResolver
        lateinit var component: AppComponent
        fun appContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        db = Room.databaseBuilder(
            appContext(),
            AppDatabase::class.java, "contacts"
        ).build()
        resolver = contentResolver
        component = DaggerAppComponent.create()
    }
}