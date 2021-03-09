package com.example.chat.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.chat.R
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("ShowToast")
fun showToast(activity: Activity, text: String) {
    Toast.makeText(activity, text, Toast.LENGTH_SHORT).show()
}

fun getDate() = SimpleDateFormat("dd MMM yyyy , HH.mm", Locale.getDefault()).format(Date())

fun ImageView.download(url: String) {
    Glide.with(context)
        .load(url)
        .centerCrop()
        .error(R.drawable.error)
        .placeholder(R.drawable.image_placeholder)
        .into(this)
}