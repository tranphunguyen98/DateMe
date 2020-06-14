package phu.nguyen.dateme.ui.swipeProfile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.swipe_profile_fragment.*
import phu.nguyen.dateme.R
import phu.nguyen.dateme.ui.dashboard.ImageProfileAdapter
import phu.nguyen.dateme.ui.dashboard.ImageProfileAdapter.Companion.NON_BORDER

class SwipeProfileFragment : Fragment() {
    private val args: SwipeProfileFragmentArgs by navArgs()

    companion object {
        fun newInstance() = SwipeProfileFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun onDetach() {
        super.onDetach()
        Log.d("testObserver", "Swipe onDetach")

    }

    private lateinit var viewModel: SwipeProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.swipe_profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.profile?.let { profile ->
            with(viewpager_swipe_profile) {
                transitionName = "profile${profile.id}"
                adapter = ImageProfileAdapter(profile.images, NON_BORDER) { it ->
                    setCurrentItem(it, true)
                }
                offscreenPageLimit = 2
                TabLayoutMediator(tab_layout_swipe_profile, this,
                    TabLayoutMediator.TabConfigurationStrategy { _, _ ->
                    }).attach()
                post {
                    setCurrentItem(args.currentItem, false)
                }
            }

            tv_name_wipe_profile.text = profile.name
            tv_age_wipe_profile.text = profile.age.toString()
            img_match_swiped.transitionName = "image"
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SwipeProfileViewModel::class.java)
        // TODO: Use the ViewModel
        img_back.setOnClickListener {
            activity?.onBackPressed()
        }

    }


    private fun setUpViewpager() {

    }

}