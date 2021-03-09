package com.example.chat.di

import com.example.chat.authentication.login.ui.LoginViewModel
import com.example.chat.authentication.singup.ui.SignUpViewModel
import com.example.chat.chat.ui.ChatViewModel
import com.example.chat.settings.ui.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { SignUpViewModel(get()) }
    viewModel { ChatViewModel(get()) }
    viewModel { SettingsViewModel(get()) }
}