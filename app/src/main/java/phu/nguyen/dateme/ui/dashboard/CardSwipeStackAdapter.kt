package phu.nguyen.dateme.ui.dashboard

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
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
        holder.bind(listImage)
    }

    class TinderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(listImage: List<Int>) =
            with(itemView) {
                viewpager_dashboard.adapter = ImageProfileAdapter(listImage) { it ->
                    viewpager_dashboard.setCurrentItem(it, true)
                }
                TabLayoutMediator(tab_layout_profile,viewpager_dashboard,
                    TabLayoutMediator.TabConfigurationStrategy { _, _ ->
                    }).attach()
                view_bottom.setOnClickListener {
                    Log.d("test e", "OPEN >>>>>")
                }
            }

    }
}