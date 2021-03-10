package com.example.chat.authentication.singup.data

import android.util.Log
import com.example.chat.data.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase


class SignUpRepositoryImpl(private val auth: FirebaseAuth, private val database: FirebaseDatabase) :
    SignUpRepository {

    // todo отправить все сроки в строковый ресурс
    override fun singUp(email: String, password: String, action: OnCompleteListener<AuthResult>) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    val id = it.result?.user?.uid ?: "1"
                    database.getReference("user")
                        .child(id)
                        .setValue(
                            User(
                                name = "Пользователь №$id",
                                email = email,
                                password = password
                            )
                        )

                    val user = auth.currentUser

                    val profileUpdates = UserProfileChangeRequest.Builder()
                        .setDisplayName("Пользователь №$id")
                        .build()

                    user!!.updateProfile(profileUpdates)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Log.d("user_update", "User profile updated.")
                            }
                        }
                }
            }.addOnCompleteListener(action)
    }
}