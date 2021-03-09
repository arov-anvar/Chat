package com.example.chat.data

data class Message(val senderName: String? = null,
                   val text: String? = null,
                   val date: String? = null,
                   val senderId: String? = null)
