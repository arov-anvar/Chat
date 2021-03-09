package com.example.chat.settings.data

import com.example.chat.data.User
import com.example.chat.settings.ui.UserCallback
import com.google.android.gms.tasks.OnCompleteListener
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

        auth.currentUser?.updatePassword(password)
            ?.addOnCompleteListener(action)
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