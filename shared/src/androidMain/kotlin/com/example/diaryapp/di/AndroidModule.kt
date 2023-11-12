package com.example.diaryapp.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.diaryapp.ImageStorage
import com.example.diaryapp.database.DiaryDatabase
import com.example.diaryapp.utils.ImageStorageImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = DiaryDatabase.Schema,
            context = androidContext(),
            name = "diary.db"
        )
    }
    single<ImageStorage> { ImageStorageImpl(androidContext()) }
}