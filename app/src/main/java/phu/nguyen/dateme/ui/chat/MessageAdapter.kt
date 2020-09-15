package phu.nguyen.dateme.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.databinding.ItemImageMessageBinding
import timber.log.Timber

class MessageAdapter(private val chats: List<Chat>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemImageMessageBinding.inflate(layoutInflater,parent,false)
        return MessageViewHolder(binding)
    }

    override fun getItemCount(): Int = chats.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(chats[position])
        Timber.d("position: $position")
    }

    class MessageViewHolder(private val binding: ItemImageMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: Chat) {
            binding.chat = chat
        }
    }
}