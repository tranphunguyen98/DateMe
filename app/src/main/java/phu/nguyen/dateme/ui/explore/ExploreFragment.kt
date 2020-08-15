package phu.nguyen.dateme.ui.explore

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
import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.databinding.FragmentExploreBinding
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class ExploreFragment : Fragment() {

    @Inject
    lateinit var factory: ExploreViewModelFactory
    lateinit var binding: FragmentExploreBinding
    private lateinit var exploreViewModel: ExploreViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        exploreViewModel =
            ViewModelProvider(this,factory).get(ExploreViewModel::class.java)

        binding = FragmentExploreBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpObserver()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

    }

    private fun setUpRecyclerView(listSwipeProfile: List<Profile>) {

        fun onJumpToProfileFragment(position: Int, currentItemVP: Int) {

            Timber.d("$position - ${listSwipeProfile.size}")

            val action =
                ExploreFragmentDirections.actionNavigationExploreToUserProfileDetailFragment(
                    listSwipeProfile[position], currentItemVP
                )

            findNavController().navigate(action)
        }

        binding.rcImagesExplore.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = ImageExploreAdapter(listSwipeProfile) { position ->
                onJumpToProfileFragment(position, 0)
            }
        }
    }

    private fun setUpObserver() {
        exploreViewModel.result.observe(viewLifecycleOwner, Observer {
            when (it) {
                is ResultProfile.Waiting -> {
                    Timber.d("Waiting 1")
                    binding.prgExplore.visibility = View.VISIBLE
                }
                is ResultProfile.Success -> {
                    Timber.d("Success 1 - ${it.profiles.size}")
                    binding.prgExplore.visibility = View.GONE
                    setUpRecyclerView(it.profiles)
                }
                is ResultProfile.Failure -> {
                    binding.prgExplore.visibility = View.GONE
                    Timber.d("Failure 1")
                }
            }
        })
    }

}