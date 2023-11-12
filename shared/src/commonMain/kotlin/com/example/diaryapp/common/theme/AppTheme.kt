package com.example.diaryapp.common.theme

import androidx.compose.runtime.Composable

@Composable
expect fun AppTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
)