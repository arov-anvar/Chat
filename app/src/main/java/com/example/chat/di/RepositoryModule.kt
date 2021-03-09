package com.example.chat.di

import com.example.chat.authentication.login.data.LoginRepository
import com.example.chat.authentication.login.data.LoginRepositoryImpl
import com.example.chat.authentication.singup.data.SignUpRepository
import com.example.chat.authentication.singup.data.SignUpRepositoryImpl
import com.example.chat.chat.data.ChatRepository
import com.example.chat.chat.data.ChatRepositoryImpl
import com.example.chat.settings.data.SettingsRepository
import com.example.chat.settings.data.SettingsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<LoginRepository> { LoginRepositoryImpl(get()) }
    single<SignUpRepository> { SignUpRepositoryImpl(get(), get()) }
    single<ChatRepository> { ChatRepositoryImpl(get(), get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get(), get()) }
}