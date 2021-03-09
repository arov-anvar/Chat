package com.example.chat.authentication.login.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chat.authentication.login.data.LoginRepository
import com.example.chat.data.Resource

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _resultLogin = MutableLiveData<Resource<Nothing>>()
    val resultLogin = _resultLogin

    fun loginBtnClicked(email: String, password: String) {
        _resultLogin.value = Resource.loading()
        if (email.isEmpty() || password.isEmpty()) _resultLogin.value = Resource.error("input password or email")
        else sendRequestLogin(email, password)
    }

    private fun sendRequestLogin(email: String, password: String) {
        loginRepository.loginWithEmailAndPassword(email, password) { result ->
            if (result.isSuccessful) {
                _resultLogin.value = Resource.success()
            } else {
                _resultLogin.value = Resource.error(result.exception?.message ?: "error")
            }
        }
    }

}