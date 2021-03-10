package com.example.chat.settings.data

import android.util.Log
import com.example.chat.data.User
import com.example.chat.settings.ui.UserCallback
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SettingsRepositoryImpl(private val auth: FirebaseAuth, private val database: FirebaseDatabase) :
    SettingsRepository {
    override fun signOut() {
        auth.signOut()
    }

    override fun saveNewData(name: String, password: String, action: OnCompleteListener<Void>) {
        database.getReference("user")
            .child(auth.uid.toString())
            .child("name")
            .setValue(name)

        val id = auth.currentUser?.uid ?: "1"
        database.getReference("user")
            .child(id)
            .get()
            .addOnSuccessListener {
                val user = it.getValue(User::class.java) ?: User()
                val credential = EmailAuthProvider.getCredential(user.email ?: "", user.password ?: "")
                auth.currentUser?.reauthenticate(credential)
                    ?.addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            database.getReference("user")
                                .child(auth.uid.toString())
                                .child("password")
                                .setValue(password)
                            auth.currentUser?.updatePassword(password)?.addOnCompleteListener(action)
                        } else {
                            Log.e("error", task.exception.toString())
                        }
                    }
            }
    }

    override fun getCurrentUser(action: UserCallback) {
        val id = auth.currentUser?.uid ?: "1"
        database.getReference("user")
            .child(id)
            .get()
            .addOnSuccessListener {
                val user = it.getValue(User::class.java) ?: User()
                action.userComing(user)
            }
    }
}