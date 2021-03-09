package com.example.chat.authentication.login.data

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult

interface LoginRepository {
    fun loginWithEmailAndPassword(
        email: String,
        password: String,
        action: OnCompleteListener<AuthResult>
    )
}