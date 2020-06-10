package phu.nguyen.dateme.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.item_swipe.view.*
import phu.nguyen.dateme.R

class CardSwipeStackAdapter(private val listImage: ArrayList<Int>, private val onItemActionListener: (viewpager : ViewPager2, position : Int) -> Unit) :
    RecyclerView.Adapter<CardSwipeStackAdapter.TinderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TinderViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_swipe, parent, false)

        return TinderViewHolder(view)
    }

    override fun getItemCount(): Int = listImage.size

    override fun onBindViewHolder(holder: TinderViewHolder, position: Int) {
        holder.bind(listImage,onItemActionListener, position)
    }

    class TinderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(listImage: List<Int>, onItemActionListener: (viewpager: ViewPager2, position : Int) -> Unit, position: Int) =
            with(itemView) {
                viewpager_dashboard.adapter = ImageProfileAdapter(listImage) { it ->
                    viewpager_dashboard.setCurrentItem(it, true)
                }
                viewpager_dashboard.transitionName = "viewpager_dashboard$position"
                TabLayoutMediator(tab_layout_profile,viewpager_dashboard,
                    TabLayoutMediator.TabConfigurationStrategy { _, _ ->
                    }).attach()
                view_bottom.setOnClickListener {
                    onItemActionListener(viewpager_dashboard, position)
                }
            }

    }
}