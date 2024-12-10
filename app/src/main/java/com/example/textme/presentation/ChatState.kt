package com.example.textme.presentation

import android.graphics.Bitmap
import com.example.textme.data.Chat

data class ChatState(
    val chatList: MutableList<Chat> = mutableListOf(),
    val prompt: String = "",
    val bitmap: Bitmap? = null,
    val isLoading: Boolean = false
)
