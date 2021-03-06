package phu.nguyen.dateme.ui.userProfileDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_user_profile_detail.*
import phu.nguyen.dateme.databinding.FragmentUserProfileDetailBinding
import timber.log.Timber

class UserProfileDetailFragment : Fragment() {
    private val args: UserProfileDetailFragmentArgs by navArgs()
    private lateinit var binding : FragmentUserProfileDetailBinding
    companion object {
        fun newInstance() = UserProfileDetailFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        sharedElementEnterTransition =
//            TransitionInflater.from(context).inflateTransition(android.R.transition.move)

    }

    override fun onDetach() {
        super.onDetach()
        Timber.d("Swipe onDetach")

    }

    private lateinit var viewModel: UserProfileDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserProfileDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        args.profile?.let { profile ->
            binding.profile = profile
            binding.tabLayout = binding.tabLayoutSwipeProfile
            with(binding.viewpagerSwipeProfile) {
                post {
                    setCurrentItem(args.currentItem, false)
                }
            }
        }

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(UserProfileDetailViewModel::class.java)
        // TODO: Use the ViewModel
        img_back.setOnClickListener {
            activity?.onBackPressed()
        }
    }


    private fun setUpViewpager() {

    }

}