package com.example.putinder.ui.chat

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.putinder.R
import java.util.*

class ChatFragment:Fragment() {
    private lateinit var messageField: EditText
    private lateinit var sendMessage: ImageButton
    private lateinit var messageRecycler: RecyclerView
    private lateinit var messageList: MutableList<Message>
    private lateinit var layoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_chat, container,false)
        init(view)
        setListeners()

        val dataMessages: MutableList<Message> = receiveMessagesFromServer()
        setDataInRecyclerView(dataMessages)
        scrollBottom()
        return view
    }

    private fun init(view: View){
        messageList = mutableListOf()
        layoutManager = LinearLayoutManager(context)

        sendMessage = view.findViewById(R.id.send_message_button)
        messageField = view.findViewById(R.id.message_et)
        messageRecycler = view.findViewById(R.id.message_recycler_view)
    }

    private fun setListeners(){
        sendMessage.setOnClickListener{
            if (messageField.text.toString().trim() != ""){
                addMessage(messageField.text.toString().trim(), Date(),true)
                messageField.setText("")
                scrollBottom()
            }
        }
    }

    private fun setDataInRecyclerView(dataMessages: MutableList<Message>){
        messageList.addAll(dataMessages)
        messageRecycler.layoutManager = layoutManager
        messageRecycler.adapter = MessageRecyclerAdapter(messageList)
    }

    private fun addMessage(message: String, date: Date, isUser: Boolean){
        messageList.add(Message(1, message, date, isUser))
    }

    private fun receiveMessagesFromServer(): MutableList<Message>{
        //FOR EXAMPLE
        val dataMessages: MutableList<Message> = mutableListOf()
        for (i in 1..20) {
            //разделим для наглядности
            if(i%2 == 0){
                dataMessages.add(Message(1,"This is my text!", Date(), true))
            }else{
                dataMessages.add(Message(1,"The text of other user", Date(), false))
            }
        }
        return dataMessages
    }


    private fun scrollBottom(){
        messageRecycler.scrollToPosition(messageList.count()-1)
    }


}
