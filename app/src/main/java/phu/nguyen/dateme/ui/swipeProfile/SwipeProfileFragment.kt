package phu.nguyen.dateme.ui.swipeProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import kotlinx.android.synthetic.main.swipe_profile_fragment.*
import phu.nguyen.dateme.R

class SwipeProfileFragment : Fragment() {
    private val args : SwipeProfileFragmentArgs by navArgs()

    companion object {
        fun newInstance() = SwipeProfileFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }
    private lateinit var viewModel: SwipeProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.swipe_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SwipeProfileViewModel::class.java)
        // TODO: Use the ViewModel
        val id = args.id
        viewpager_swipe_profile.transitionName = id
//        viewpager_swipe_profile.adapter = ImageProfileAdapter(listImageSwipe) { it ->
//            viewpager_swipe_profile.setCurrentItem(it, true)
//        }
    }

}