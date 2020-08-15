package phu.nguyen.dateme.ui.likes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import phu.nguyen.dateme.common.ResultProfile
import phu.nguyen.dateme.data.ProfileRepository
import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.databinding.FragmentInteractionBinding
import timber.log.Timber
import javax.inject.Inject

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_INTERACTIVE_TYPE = "interactiveType"

/**
 * A simple [Fragment] subclass.
 * Use the [InteractionFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class InteractionFragment : Fragment() {
    @Inject
    lateinit var repository: ProfileRepository
    lateinit var factory: InteractionViewModelFactory

    lateinit var binding: FragmentInteractionBinding
    private lateinit var viewModel: InteractionViewModel

    private var interactiveType: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            interactiveType = it.getInt(ARG_INTERACTIVE_TYPE, -1)
        }
        factory = InteractionViewModelFactory(repository,interactiveType)

        viewModel = ViewModelProvider(this,factory).get(InteractionViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInteractionBinding.inflate(inflater, container,false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
    }

    private fun setUpRecyclerView(profiles: List<Profile>) {

        fun onJumpToProfileFragment(position: Int, currentItemVP: Int) {

            Timber.d("$position - ${profiles.size}")

            val action =
               InteractionFragmentDirections.actionInteractionFragmentToUserProfileDetailFragment(
                    profiles[position], currentItemVP
                )

            findNavController().navigate(action)
        }

        binding.rcImagesInteraction.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = ImageInteractionAdapter(profiles) { position ->
                onJumpToProfileFragment(position, 0)
            }
        }
    }

    private fun setUpObserver() {
        viewModel.result.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultProfile.Waiting -> {
                    Timber.d("Waiting 2")
                    binding.prgInteraction.visibility = View.VISIBLE
                }
                is ResultProfile.Success -> {
                    Timber.d("Success 2 - ${it.profiles.size}")
                    binding.prgInteraction.visibility = View.GONE
                    setUpRecyclerView(it.profiles)
                }
                is ResultProfile.Failure -> {
                    binding.prgInteraction.visibility = View.GONE
                    Timber.d("Failure 2")
                }
            }
        })
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param interactiveType Parameter 1.
         * @return A new instance of fragment InteractionFragment.
         */
        @JvmStatic
        fun newInstance(interactiveType: Int) =
            InteractionFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_INTERACTIVE_TYPE, interactiveType)
                }
            }
    }
}