package phu.nguyen.dateme.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.item_swipe.view.*
import phu.nguyen.dateme.R
import phu.nguyen.dateme.data.model.Profile
import kotlin.random.Random

class CardSwipeStackAdapter(private val listProfiles: List<Profile>, private val onItemActionListener: (viewpager : ViewPager2, position : Int) -> Unit) :
    RecyclerView.Adapter<CardSwipeStackAdapter.TinderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TinderViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_swipe, parent, false)

        return TinderViewHolder(view)
    }

    override fun getItemCount(): Int = listProfiles.size

    override fun onBindViewHolder(holder: TinderViewHolder, position: Int) {
        holder.bind(listProfiles[position],onItemActionListener, position)
    }

    class TinderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(profile: Profile, onItemActionListener: (viewpager: ViewPager2, position : Int) -> Unit, position: Int) =
            with(itemView) {
                tv_name.text = profile.name
                tv_age.text = profile.age.toString()
                tv_distance.text = "Cách bạn ${Random.nextInt(1,20)}Km"
                viewpager_dashboard.adapter = ImageProfileAdapter(profile.images) { it ->
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