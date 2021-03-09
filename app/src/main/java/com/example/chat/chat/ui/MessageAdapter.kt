package com.example.chat.chat.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chat.R
import com.example.chat.data.Message
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.my_message.view.messageTxt
import kotlinx.android.synthetic.main.other_message.view.*

class MessageAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val MY = 1
    private val OTHER = 2

    private val messages = mutableListOf<Message>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            MY -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.my_message, parent, false)
                MyMessageViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.other_message, parent, false)
                OtherMessageViewHolder(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MyMessageViewHolder -> holder.bind(messages[position])
            is OtherMessageViewHolder -> holder.bind(messages[position])
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun getItemViewType(position: Int): Int {
        return if (messages[position].senderId == FirebaseAuth.getInstance().uid) MY
        else OTHER
    }

    fun addMessage(message: Message?) {
        message?.run {
            messages.add(this)
            notifyDataSetChanged()
        }
    }

    inner class MyMessageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(message: Message) {
            view.messageTxt.text = message.text
        }
    }

    inner class OtherMessageViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(message: Message) {
            view.run {
                messageTxt.text = message.text
                userNameTxt.text = message.senderName
            }
        }
    }
}