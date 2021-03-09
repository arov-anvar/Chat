package com.example.chat.authentication.login.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chat.R
import com.example.chat.chat.MainActivity
import com.example.chat.data.Status
import com.example.chat.utils.showToast
import kotlinx.android.synthetic.main.fragment_login.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loginBtn.setOnClickListener {
            viewModel.loginBtnClicked(emailET.text.toString(), passwordET.text.toString())
        }

        viewModel.resultLogin.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.SUCCESS -> successLogin()
                Status.ERROR -> errorLogin(it.message ?: "")
                Status.LOADING -> progressBar.visibility = View.VISIBLE
            }
        })
    }

    private fun successLogin() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        requireActivity().run {
            startActivity(intent)
            finish()
        }
        progressBar.visibility = View.GONE
    }

    private fun errorLogin(messageError: String) {
        showToast(requireActivity(), messageError)
        progressBar.visibility = View.GONE
    }
}