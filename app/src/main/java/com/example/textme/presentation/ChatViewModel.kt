package com.example.textme.presentation

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.textme.data.Chat
import com.example.textme.data.ChatData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {

    private val _chatState = MutableStateFlow(ChatState())
    val chatState = _chatState.asStateFlow()

    fun onEvent(event: ChatUiEvent) {
        when (event) {
            is ChatUiEvent.SendPrompt -> {

                if (event.prompt.isNotEmpty()) {

                    addPrompt(event.prompt, event.bitmap)
                    _chatState.update { it.copy( isLoading = true ) }

                    if (event.bitmap != null) {
                        getResponseWithImage(event.prompt, event.bitmap)

                    } else {
                        getResponse(event.prompt)
                    }
                }
            }

            is ChatUiEvent.UpdatePrompt -> {
                _chatState.update {
                    it.copy(
                        prompt = event.newPrompt
                    )
                }
            }


        }
    }


    private fun addPrompt(prompt: String, bitmap: Bitmap?) {
        _chatState.update {
            it.copy(
                chatList = it.chatList.toMutableList().apply {
                    add(0, Chat(prompt = prompt, bitmap = bitmap, isFromUser = true))
                },
                prompt = "",
                bitmap = null
            )
        }
    }

    private fun getResponse(prompt: String) {
        viewModelScope.launch {
            try {
                val chat = ChatData.getResponse(prompt)
                _chatState.update {
                    it.copy(
                        chatList = it.chatList.toMutableList().apply {
                            add(0, chat)
                        },
                        isLoading = false // Toggle loading to false after the response
                    )
                }
            } catch (e: Exception) {
                // Handle the error
                _chatState.update { it.copy(isLoading = false) }
            }
        }
    }

    private fun getResponseWithImage(prompt: String, bitmap: Bitmap) {
        viewModelScope.launch {
            try {
                val chat = ChatData.getResponseWithImage(prompt, bitmap)
                _chatState.update {
                    it.copy(
                        chatList = it.chatList.toMutableList().apply {
                            add(0, chat)
                        },
                        isLoading = false // Toggle loading to false after the response
                    )
                }
            } catch (e: Exception) {
                // Handle the error
                _chatState.update { it.copy(isLoading = false) }
            }
        }
    }


}