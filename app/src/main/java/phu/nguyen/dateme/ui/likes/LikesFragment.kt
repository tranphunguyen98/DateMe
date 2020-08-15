package phu.nguyen.dateme.ui.likes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import phu.nguyen.dateme.databinding.FragmentLikesBinding

class LikesFragment : Fragment() {
    private lateinit var binding: FragmentLikesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLikesBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val likesAdapter = LikesAdapter(requireActivity() as AppCompatActivity ,2)
        binding.vpgLikes.adapter = likesAdapter

        TabLayoutMediator(binding.tlLikes,binding.vpgLikes) {tab, position ->
            when(position) {
                0 -> {
                    tab.text = "LIKES YOU"
                }
                1 -> {
                    tab.text = "VISITORS"
                }
                else -> {
                    tab.text = "NOT DEFINE"
                }
            }
        }.attach()

    }
}