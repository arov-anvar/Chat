package com.example.chat.authentication.singup.data

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult

interface SignUpRepository {
    fun singUp(email: String, password: String, action: OnCompleteListener<AuthResult>)
}