package phu.nguyen.dateme.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import phu.nguyen.dateme.databinding.ItemImageMessageBinding
import timber.log.Timber

class MessageAdapter() : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemImageMessageBinding.inflate(layoutInflater,parent,false)
        return MessageViewHolder(binding)
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind()
        Timber.d("position: $position")
    }

    class MessageViewHolder(private val binding: ItemImageMessageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }
}