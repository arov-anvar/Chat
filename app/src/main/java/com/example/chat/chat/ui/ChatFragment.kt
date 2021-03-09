package com.example.chat.chat.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chat.R
import com.example.chat.data.Status
import com.example.chat.utils.showToast
import kotlinx.android.synthetic.main.fragment_chat.*
import kotlinx.android.synthetic.main.fragment_chat.progressBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChatFragment : Fragment(R.layout.fragment_chat) {

    private val adapterMessage = MessageAdapter()
    private val viewModel: ChatViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        chatRV.run {
            adapter = adapterMessage
            layoutManager = LinearLayoutManager(requireContext()).apply { stackFromEnd = true }
        }

        viewModel.getMessages()
        viewModel.listMessages.observe(viewLifecycleOwner, {
            when(it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    adapterMessage.addMessage(it.data)
                }
                Status.ERROR -> errorDownloadMessages(it.message ?: "")
                Status.LOADING -> progressBar.visibility = View.VISIBLE
            }

        })

        sendBtn.setOnClickListener {
            viewModel.sendBtnClicked(messageET.text.toString())
            messageET.text.clear()
        }

    }

    private fun errorDownloadMessages(message: String) {
        progressBar.visibility = View.GONE
        showToast(requireActivity(), message)
    }
}