package com.example.diaryapp.di

import com.example.diaryapp.DiaryDataSource
import com.example.diaryapp.data.local.DiaryLocalDataSourceImpl
import com.example.diaryapp.database.DiaryDatabase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private val dataModule = module {
    single<DiaryDataSource.Local> {
        DiaryLocalDataSourceImpl(
            database = DiaryDatabase(driver = get()),
            imageStorage = get()
        )
    }
}

private val sharedModules = listOf(
    dataModule,
)

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(sharedModules)
}