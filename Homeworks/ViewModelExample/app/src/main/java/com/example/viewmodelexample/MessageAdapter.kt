package com.example.viewmodelexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter (private val messages : List<String>) : RecyclerView.Adapter<MessageAdapter.MessageHolder>() {

    inner class MessageHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val msg: TextView = itemView.findViewById(R.id.msg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageHolder =
        MessageHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_msg, parent, false))

    override fun onBindViewHolder(holder: MessageHolder, position: Int) {
        holder.msg.text = messages[position]
    }

    override fun getItemCount(): Int = messages.size
}