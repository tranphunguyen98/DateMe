package phu.nguyen.dateme.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_profile.*
import phu.nguyen.dateme.R
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.databinding.FragmentProfileBinding
import phu.nguyen.dateme.ui.main.HomeActivity
import timber.log.Timber

class ProfileFragment : Fragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var binding: FragmentProfileBinding
    private lateinit var user : User

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_setting.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_settingFragment)
        }
        btn_edit.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_editProfileFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        user = (activity as HomeActivity).user
        Timber.d(user.userBasicInfo.name)

        binding.userBasicInfo = user.userBasicInfo
    }
}