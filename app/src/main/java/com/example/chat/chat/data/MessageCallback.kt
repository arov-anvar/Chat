package com.example.chat.chat.data

import com.example.chat.data.Message

interface MessageCallback {
    fun messageComing(message: Message)
}