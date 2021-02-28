package com.example.viewmodelexample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_person_a.*

class PersonA : Fragment() {

    private lateinit var personViewModel: PersonViewModel
    private lateinit var messageAdapter: MessageAdapter
    private val messages = ArrayList<String>()
    private var message: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_person_a, container, false)
    }

    override fun onResume() {
        super.onResume()

        personViewModel = ViewModelProvider(requireActivity(), ViewModelProvider.NewInstanceFactory())
                .get(PersonViewModel::class.java)

        sendBtn.setOnClickListener {
            val msg = msgBox.text.toString()
            if (msg.isNotEmpty() && !msg.contentEquals(message)) {
                personViewModel.messageB.value = msg
                message = msg
                msgBox.setText("")
            }
        }

        personViewModel.messageA.observe(requireActivity(), {msg ->
            messages.add(msg)
            messageAdapter.notifyItemInserted(messages.size)
        })

        messageAdapter = MessageAdapter(messages)
        val lManager = LinearLayoutManager(requireActivity()).apply { reverseLayout = true }
        msgListA.apply {
            layoutManager = lManager
            adapter = messageAdapter
        }
    }
}