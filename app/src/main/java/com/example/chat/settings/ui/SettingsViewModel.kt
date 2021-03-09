package com.example.chat.settings.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chat.data.Resource
import com.example.chat.data.User
import com.example.chat.settings.data.SettingsRepository

class SettingsViewModel(private val repository: SettingsRepository) : ViewModel() {

    private val _resultChange = MutableLiveData<Resource<Nothing>>()
    val resultChange = _resultChange

    private val _currentUser = MutableLiveData<Resource<User>>()
    val currentUser = _currentUser

    fun getCurrentUser() {
        _currentUser.value = Resource.loading()
        repository.getCurrentUser(object : UserCallback {
            override fun userComing(user: User) {
                _currentUser.value = Resource.success(user)
            }
        })
    }

    fun signOutBtnClicked() {
        repository.signOut()
    }

    fun saveBtnClicked(name: String, password: String) {
        _resultChange.value = Resource.loading()
        if (isDataCorrectly(name, password)) {
            repository.saveNewData(name, password) {
                if (it.isSuccessful) _resultChange.value = Resource.success()
                else _resultChange.value = Resource.error(it.exception?.message.toString())
            }
        }
    }

    private fun isDataCorrectly(name: String, password: String): Boolean {
        if (name.isEmpty() || password.isEmpty()) {
            _resultChange.value = Resource.error("input name and password")
            return false
        }

        if (!name.matches(Regex("[а-яА-ЯёЁ]+"))) {
            _resultChange.value = Resource.error("Имя должно быть на русском")
            return false
        }

        if (!name.matches(Regex("[А-ЯЁ].*"))) {
            _resultChange.value = Resource.error("Имя должно быть с большой буквы")
            return false
        }

        return true
    }
}