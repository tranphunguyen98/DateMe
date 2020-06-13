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
import kotlinx.android.synthetic.main.swipe_profile_fragment.*
import phu.nguyen.dateme.R
import phu.nguyen.dateme.ui.dashboard.ImageProfileAdapter

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
        args.profile?.let {profile ->
            with(viewpager_swipe_profile){
                transitionName = "profile${profile.id}"
                adapter = ImageProfileAdapter(profile.images) { it ->
                    viewpager_swipe_profile.setCurrentItem(it, true)
                }
                offscreenPageLimit = 2
            }
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