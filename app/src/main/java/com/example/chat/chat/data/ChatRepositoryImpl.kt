package com.example.chat.chat.data

import com.example.chat.data.Message
import com.example.chat.data.User
import com.example.chat.utils.getDate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase

class ChatRepositoryImpl(private val auth: FirebaseAuth, private val database: FirebaseDatabase) :
    ChatRepository {

    override fun sendMessage(messageText: String) {
        val id = auth.uid ?: ""
        database.getReference("user").child(id).get()
            .addOnSuccessListener {
                val currentUser = it.getValue(User::class.java)
                val name = currentUser?.name ?: ""
                val message = Message(
                    senderName = name,
                    text = messageText,
                    date = getDate().toString(),
                    senderId = id
                )
                val key = FirebaseDatabase.getInstance().getReference("chat").push().key
                FirebaseDatabase.getInstance().getReference("chat")
                    .child(key ?: "1")
                    .setValue(message)
            }
    }

    override fun getMessages(action: MessageCallback) {
        database.getReference("chat")
            .addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                    val message = snapshot.getValue(Message::class.java) as Message
                    action.messageComing(message)
                }
                override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onChildRemoved(snapshot: DataSnapshot) {}
                override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {}
                override fun onCancelled(error: DatabaseError) {}

            })
    }
}