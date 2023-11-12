package com.example.diaryapp.data.local

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.diaryapp.DiaryDataSource
import com.example.diaryapp.ImageStorage
import com.example.diaryapp.data.model.Diary
import com.example.diaryapp.database.DiaryDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.supervisorScope
import kotlinx.datetime.Clock

class DiaryLocalDataSourceImpl(
    database: DiaryDatabase,
    private val imageStorage: ImageStorage
): DiaryDataSource.Local {
    private val queries = database.diaryQueries

    override fun getDiary(id: Long): Flow<Diary> {
        return queries
            .getDiary(id)
            .asFlow()
            .map { diaryEntity ->
                val result = diaryEntity.executeAsOneOrNull()
                result?.toDiary(imageStorage = imageStorage) ?: Diary.empty
            }
    }

    override fun getDiaries(): Flow<List<Diary>> {
        return queries
            .getDiaries()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { diaryEntities ->
                supervisorScope {
                    diaryEntities
                        .map {
                            async { it.toDiary(imageStorage = imageStorage) }
                        }
                        .map { it.await() }
                }
            }
    }

    override fun getRecentDiaries(amount: Long): Flow<List<Diary>> {
        return queries
            .getRecentDiaries(amount)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { diaryEntities ->
                supervisorScope {
                    diaryEntities
                        .map {
                            async { it.toDiary(imageStorage = imageStorage) }
                        }
                        .map { it.await() }
                }
            }
    }

    override fun getDiariesByTag(tag: String): Flow<List<Diary>> {
        return queries
            .getDiariesByTag(tag = tag)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { diaryEntities ->
                supervisorScope {
                    diaryEntities
                        .map {
                            async { it.toDiary(imageStorage = imageStorage) }
                        }
                        .map { it.await() }
                }
            }
    }

    override fun getDiariesByDate(startDate: Long, endDate: Long): Flow<List<Diary>> {
        return queries
            .getDiariesByDate(startDate = startDate, endDate = endDate)
            .asFlow()
            .mapToList(Dispatchers.IO)
            .map { diaryEntities ->
                supervisorScope {
                    diaryEntities
                        .map {
                            async { it.toDiary(imageStorage = imageStorage) }
                        }
                        .map { it.await() }
                }
            }
    }

    override suspend fun insertDiary(diary: Diary) {
        val imagePath = diary.imageBytes?.let {
            imageStorage.saveImage(it)
        }

        queries.insertDiaryEntity(
            id = diary.id,
            tag = diary.tag,
            title = diary.title,
            content = diary.content,
            imagePath = imagePath,
            createdAt = Clock.System.now().toEpochMilliseconds(),
        )
    }

    override suspend fun deleteDiary(id: Long) {
        val entity = queries.getDiary(id).executeAsOneOrNull()
        entity?.imagePath?.let { imageStorage.deleteImage(it) }
        queries.deleteDiary(id = id)
    }
}