package com.example.diaryapp
import com.example.diaryapp.data.model.Diary
import kotlinx.coroutines.flow.Flow

interface DiaryDataSource {
    interface Local {
        fun getDiary(id: Long): Flow<Diary>
        fun getDiaries(): Flow<List<Diary>>
        fun getRecentDiaries(amount: Long): Flow<List<Diary>>
        fun getDiariesByTag(tag: String): Flow<List<Diary>>
        fun getDiariesByDate(startDate: Long, endDate: Long): Flow<List<Diary>>
        suspend fun insertDiary(diary: Diary)
        suspend fun deleteDiary(id: Long)
    }
}