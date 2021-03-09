package com.example.chat.settings.data

import com.example.chat.settings.ui.UserCallback
import com.google.android.gms.tasks.OnCompleteListener

interface SettingsRepository {
    fun signOut()
    fun saveNewData(name: String, password: String, action: OnCompleteListener<Void>)
    fun getCurrentUser(action: UserCallback)
}