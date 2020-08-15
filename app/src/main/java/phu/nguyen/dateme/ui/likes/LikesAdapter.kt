package phu.nguyen.dateme.ui.likes

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import phu.nguyen.dateme.data.model.Interaction

class LikesAdapter(activity: AppCompatActivity, private val itemsCount: Int) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = itemsCount

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> InteractionFragment.newInstance(Interaction.LIKE_YOU)
            1 -> InteractionFragment.newInstance(Interaction.VISIT_YOU)
            else -> throw UnsupportedOperationException()
        }
    }

}