package com.example.chat.chat.data

interface ChatRepository {
    fun sendMessage(messageText: String)
    fun getMessages(action: MessageCallback)
}