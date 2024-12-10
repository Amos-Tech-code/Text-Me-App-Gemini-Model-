/*package com.example.textme.view

import android.graphics.Bitmap
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddPhotoAlternate
import androidx.compose.material.icons.rounded.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.textme.MainActivity
import com.example.textme.presentation.ChatUiEvent
import com.example.textme.presentation.ChatViewModel
import kotlinx.coroutines.flow.update

@Composable
fun ChatScreen2(paddingValues: PaddingValues) {

    val chatViewModel = viewModel<ChatViewModel>()
    val chatState = chatViewModel.chatState.collectAsState()

    val bitmap = MainActivity().getBitMap()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
        verticalArrangement = Arrangement.Bottom
    ) {
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            reverseLayout = true
        ) {
            itemsIndexed(chatState.value.chatList) { index, chat ->

                if (chat.isFromUser) {
                    chat.bitmap?.let {
                        UserChatItem(
                            prompt = chat.prompt,
                            bitmap = it
                        )
                    }
                } else {
                    ModelChatItem(response = chat.prompt)
                }
                
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                bitmap?.let {
                    Image(
                        bitmap = it.asImageBitmap(),
                        contentDescription = "Picked image",
                        modifier = Modifier
                            .padding(bottom = 2.dp)
                            .size(40.dp)
                            .clip(RoundedCornerShape(6.dp)),
                        contentScale = ContentScale.Crop

                    )
                }

                Icon(
                    imageVector = Icons.Rounded.AddPhotoAlternate,
                    contentDescription = "Add image",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(40.dp)
                        .clickable {
                            MainActivity().imagePicker.launch(
                                PickVisualMediaRequest
                                    .Builder()
                                    .setMediaType(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    .build() as Nothing
                            )
                        }
                )

            }
            Spacer(modifier = Modifier.width(8.dp))

            TextField(
                value = chatState.value.prompt,
                onValueChange = {
                    chatViewModel.onEvent(ChatUiEvent.UpdatePrompt(it))
                },
                placeholder = { Text(text = "Message me")},
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))

            Icon(
                imageVector = Icons.Rounded.Send,
                contentDescription = "Send message",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(40.dp)
                    .clickable {
                        chatViewModel.onEvent(ChatUiEvent.SendPrompt(chatState.value.prompt, chatState.value.bitmap))
                        MainActivity().uriState.update { "" }
                    }
            )

        }
    }
}




@Composable
fun UserChatItem(prompt: String, bitmap: Bitmap) {
    Column(
        modifier = Modifier
            .padding(start = 100.dp, bottom = 22.dp)
    ) {

        bitmap?.let {
            Image(
                bitmap = it.asImageBitmap(),
                contentDescription = "Image",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 2.dp)
                    .height(260.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop

            )
        }

        Text(
            text = prompt,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.primary)
        )
    }
}


@Composable
fun ModelChatItem(response: String) {
    Column(
        modifier = Modifier
            .padding(end = 100.dp, bottom = 22.dp)
    ) {

        Text(
            text = response,
            fontSize = 17.sp,
            color = MaterialTheme.colorScheme.onTertiary,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.tertiary)
        )
    }
}*/