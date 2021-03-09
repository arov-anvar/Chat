package com.example.chat.authentication.singup.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chat.R
import com.example.chat.chat.MainActivity
import com.example.chat.data.Status
import com.example.chat.utils.showToast
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.android.synthetic.main.fragment_sign_up.emailET
import kotlinx.android.synthetic.main.fragment_sign_up.passwordET
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val viewModel: SignUpViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        singUpBtn.setOnClickListener {
            viewModel.singUpBtnClicked(
                emailET.text.toString(),
                passwordET.text.toString(),
                confirmPasswordET.text.toString()
            )
        }

        viewModel.resultSignUp.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.SUCCESS -> successSignUp()
                Status.ERROR -> errorSignUp(it.message ?: "")
                Status.LOADING -> progressBar.visibility = View.VISIBLE
            }
        })

    }

    private fun successSignUp() {
        val intent = Intent(requireActivity(), MainActivity::class.java)
        requireActivity()
            .startActivity(intent)
        requireActivity().finish()
        progressBar.visibility = View.GONE
    }

    private fun errorSignUp(messageError: String) {
        showToast(requireActivity(), messageError)
        progressBar.visibility = View.GONE
    }
}
