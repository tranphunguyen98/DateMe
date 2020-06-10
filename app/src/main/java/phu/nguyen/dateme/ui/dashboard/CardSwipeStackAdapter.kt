package phu.nguyen.dateme.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_swipe.view.*
import phu.nguyen.dateme.R

class CardSwipeStackAdapter(private val listImage: ArrayList<Int>) :
    RecyclerView.Adapter<CardSwipeStackAdapter.TinderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TinderViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_swipe, parent, false)

        return TinderViewHolder(view)
    }

    override fun getItemCount(): Int = listImage.size

    override fun onBindViewHolder(holder: TinderViewHolder, position: Int) {

        holder.itemView.imgTinderSwipe.setImageResource(listImage[position])

    }

    class TinderViewHolder(view: View) : RecyclerView.ViewHolder(view)
}