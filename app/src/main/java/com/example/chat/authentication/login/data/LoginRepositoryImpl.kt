package com.example.chat.authentication.login.data

import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class LoginRepositoryImpl(private val auth: FirebaseAuth) : LoginRepository {

    override fun loginWithEmailAndPassword(
        email: String,
        password: String,
        action: OnCompleteListener<AuthResult>
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(action)
    }

}