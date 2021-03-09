package com.example.chat.settings.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.chat.LaunchActivity
import com.example.chat.R
import com.example.chat.data.Status
import com.example.chat.utils.showToast
import kotlinx.android.synthetic.main.fragment_settings.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val viewModel: SettingsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCurrentUser()
        viewModel.currentUser.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.SUCCESS -> {
                    it.data?.run {
                        nameET.setText(name)
                        emailTxt.text = email
                    }
                    progressBar.visibility = View.GONE
                }
                Status.ERROR -> showToast(requireActivity(), it.message.toString())
                Status.LOADING -> progressBar.visibility = View.VISIBLE
            }
        })

        saveBtn.setOnClickListener {
            viewModel.saveBtnClicked(nameET.text.toString(), passwordET.text.toString())
            viewModel.resultChange.observe(viewLifecycleOwner, {
                when(it.status) {
                    Status.SUCCESS -> {
                        progressBar.visibility = View.GONE
                        showToast(requireActivity(), "Данные изменены")
                    }
                    Status.ERROR -> showToast(requireActivity(), it.message.toString())
                    Status.LOADING -> progressBar.visibility = View.VISIBLE
                }
            })
        }

        signOutBtn.setOnClickListener {
            viewModel.signOutBtnClicked()
            requireActivity().apply {
                val intent = Intent(this, LaunchActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}