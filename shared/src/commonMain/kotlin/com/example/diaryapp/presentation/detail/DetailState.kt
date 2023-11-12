package com.example.diaryapp.presentation.detail

data class DetailState(
    val validate: Boolean = false,
    val message: String = "",
    val completed: Boolean = false,
)