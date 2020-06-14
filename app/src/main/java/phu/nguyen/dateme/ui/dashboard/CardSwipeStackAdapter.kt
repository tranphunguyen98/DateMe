package phu.nguyen.dateme.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.databinding.ItemSwipeBinding
import kotlin.random.Random

class CardSwipeStackAdapter(
    private val listProfiles: MutableList<Profile>,
    private val onItemActionListener: (viewpager: ViewPager2, position: Int, currentItemVP: Int) -> Unit
) :
    RecyclerView.Adapter<CardSwipeStackAdapter.TinderViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TinderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemSwipeBinding.inflate(layoutInflater, parent, false)
        return TinderViewHolder(itemBinding)
    }

    override fun getItemCount(): Int = listProfiles.size

    override fun onBindViewHolder(holder: TinderViewHolder, position: Int) {
//        Log.d("testBindCard", "bindC $position")
        holder.bind(listProfiles[position], onItemActionListener, position)
    }

    inner class TinderViewHolder(private val binding: ItemSwipeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(
            _profile: Profile,
            onItemActionListener: (viewpager: ViewPager2, position: Int, currentItemVP: Int) -> Unit,
            position: Int
        ) =
            with(binding) {
                profile = _profile
                tabLayout = binding.tabLayoutProfile
                tvDistance.text = "Cách bạn ${Random.nextInt(1, 20)}Km"

                viewBottom.setOnClickListener {
                    onItemActionListener(
                        viewpagerDashboard,
                        position,
                        viewpagerDashboard.currentItem
                    )
                }
            }

    }
}