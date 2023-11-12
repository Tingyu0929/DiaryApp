package com.example.diaryapp.android

import android.app.Application
import com.example.diaryapp.di.appModule
import com.example.diaryapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class DiaryApplication: Application(){
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(applicationContext)
            modules(appModule)
        }
    }

}