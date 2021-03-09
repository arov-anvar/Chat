package com.example.chat.settings.ui

import com.example.chat.data.User

interface UserCallback {
    fun userComing(user: User)
}