package com.example.diaryapp

import App
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.diaryapp.presentation.main.components.ShortcutButton


@Composable
fun MainView(){
    App(
        darkTheme = isSystemInDarkTheme(),
        dynamicColor = false
    )
}

@Preview
@Composable
fun TEST(){
    ShortcutButton(
        text = "Add",
        icon = Icons.Rounded.Add,
        background = MaterialTheme.colorScheme.primary,
        onShortcutClicked = {},
        modifier = Modifier.padding(16.dp)
    )
}