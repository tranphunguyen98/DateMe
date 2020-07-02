package phu.nguyen.dateme.ui.editProfile

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.databinding.FragmentEditProfileBinding
import phu.nguyen.dateme.ui.main.HomeActivity
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    @Inject
    lateinit var factory: EditProfileViewModelFactory

    private lateinit var viewModel: EditProfileViewModel
    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var user: User

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        user = (activity as HomeActivity).user
        viewModel = ViewModelProvider(this,factory).get(EditProfileViewModel::class.java)
        viewModel.setMyProfile(user.myProfile)
        binding.viewModel = viewModel

        setUpUI()
    }

    private fun setUpUI() {
        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        binding.rcImages.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = ImageEditProfileAdapter(viewModel.myProfile.images, displayMetrics.heightPixels)
        }
    }

}