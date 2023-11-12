package com.example.diaryapp.presentation.main

sealed interface MainEvent {
    data object OnCalendarClicked: MainEvent
    data class OnCalendarSelected(val value: Long): MainEvent
    data class OnTagSelected(val tag: String): MainEvent
}