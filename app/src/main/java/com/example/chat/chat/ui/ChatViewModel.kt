package com.example.chat.chat.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chat.chat.data.ChatRepository
import com.example.chat.chat.data.MessageCallback
import com.example.chat.data.Message
import com.example.chat.data.Resource

class ChatViewModel(private val repository: ChatRepository) : ViewModel() {

    private val _listMessages = MutableLiveData<Resource<Message>>()
    val listMessages = _listMessages

    fun sendBtnClicked(message: String) {
        if (message.isNotEmpty()) repository.sendMessage(message)
    }

    fun getMessages() {
        _listMessages.value = Resource.loading()
        repository.getMessages(object : MessageCallback {
            override fun messageComing(message: Message) {
                _listMessages.value = Resource.success(message)
            }
        })
    }
}