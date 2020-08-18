package phu.nguyen.dateme.ui.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import phu.nguyen.dateme.databinding.ItemImageNewMatchBinding
import timber.log.Timber

class NewMatchAdapter() : RecyclerView.Adapter<NewMatchAdapter.NewMatchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewMatchViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemImageNewMatchBinding.inflate(layoutInflater,parent,false)
        return NewMatchViewHolder(binding)
    }

    override fun getItemCount(): Int = 5

    override fun onBindViewHolder(holder: NewMatchViewHolder, position: Int) {
        holder.bind()
        Timber.d("position: $position")
    }

    class NewMatchViewHolder(private val binding: ItemImageNewMatchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }
}