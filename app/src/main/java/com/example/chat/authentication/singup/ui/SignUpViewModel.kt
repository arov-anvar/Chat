package com.example.chat.authentication.singup.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chat.authentication.singup.data.SignUpRepository
import com.example.chat.data.Resource

class SignUpViewModel(private val repository: SignUpRepository) : ViewModel() {

    private val _resultSingUp = MutableLiveData<Resource<Nothing>>()
    val resultSignUp = _resultSingUp

    fun singUpBtnClicked(email: String, password: String, confirmPassword: String) {
        if (isDataCorrectly(email, password, confirmPassword)) {
            repository.singUp(email, password) { result ->
                if (result.isSuccessful) {
                    _resultSingUp.value = Resource.success()
                } else {
                    _resultSingUp.value = Resource.error(result.exception?.message ?: "error")
                }
            }
        }
    }

    private fun isDataCorrectly(email: String, password: String, confirmPassword: String): Boolean {
        return if (email.isEmpty() || password.isEmpty()) {
            _resultSingUp.value = Resource.error("input password or email")
            false
        } else if(password != confirmPassword) {
            _resultSingUp.value = Resource.error("password don't confirm")
            false
        } else true
    }
}