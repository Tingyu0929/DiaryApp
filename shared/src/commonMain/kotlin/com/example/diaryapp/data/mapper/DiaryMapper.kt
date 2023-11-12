package com.example.diaryapp.data.local

import com.example.diaryapp.ImageStorage
import com.example.diaryapp.data.model.Diary
import com.example.diaryapp.database.DiaryEntity

suspend fun DiaryEntity.toDiary(imageStorage: ImageStorage): Diary {
    return Diary(
        id = id,
        tag = tag,
        title = title,
        content = content,
        imageBytes = imagePath?.let { imageStorage.getImage(it) },
        date = createdAt
    )
}