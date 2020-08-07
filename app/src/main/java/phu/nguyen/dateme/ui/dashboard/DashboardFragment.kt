package phu.nguyen.dateme.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import phu.nguyen.dateme.R
import phu.nguyen.dateme.common.ResultProfile
import phu.nguyen.dateme.data.model.Matching
import phu.nguyen.dateme.data.model.SwipeProfile
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    @Inject
    lateinit var factory: DashboardViewModelFactory

    private lateinit var viewModel: DashboardViewModel
    private lateinit var cardAdapter: CardSwipeStackAdapter
    private lateinit var cardManager: CardStackLayoutManager
    private lateinit var swipeProfiles: List<SwipeProfile>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel =
            ViewModelProvider(this, factory).get(DashboardViewModel::class.java)

        Timber.d("onCreateView")


        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("onViewCreated")
        img_match.transitionName = "image"
        setUpObserver()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Timber.d("onActivityCreated")
//        if(savedInstanceState == null) {
//            dashboardViewModel.getData()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onDetach() {
        super.onDetach()
    }

    private val cardStackLListener = object : CardStackListener {
        override fun onCardDisappeared(view: View?, position: Int) {
        }

        override fun onCardDragging(direction: Direction?, ratio: Float) {
        }

        override fun onCardSwiped(direction: Direction?) {
            when (direction) {
                Direction.Left -> {
                    viewModel.saveMatching(
                        Matching(
                            swipeProfiles[cardManager.topPosition - 1].id,
                            Matching.DISLIKE
                        )
                    )
                }
                Direction.Top -> {
                    Timber.d("Top")
                    Timber.d("Bottom")
                    viewModel.saveMatching(
                        Matching(
                            swipeProfiles[cardManager.topPosition - 1].id,
                            Matching.SUPER_LIKE
                        )
                    )
                }
                Direction.Bottom -> {

                }
                Direction.Right -> {
//                    dashboardViewModel.removeTop()
                    Timber.d("Right")
                    viewModel.saveMatching(
                        Matching(
                            swipeProfiles[cardManager.topPosition - 1].id,
                            Matching.LIKE
                        )
                    )
                }
            }
        }

        override fun onCardCanceled() {
        }

        override fun onCardAppeared(view: View?, position: Int) {
        }

        override fun onCardRewound() {
        }

    }

    private fun setUpCardStackView() {
        fun onJumpToProfileFragment(viewpager: ViewPager2, position: Int, currentItemVP: Int) {
            Timber.d("$position - ${swipeProfiles.size}")
            val extras = FragmentNavigatorExtras(
                viewpager to "profile${swipeProfiles[position].id}",
                img_match to "image"
            )
            val action =
                DashboardFragmentDirections.actionNavigationDashboardToSwipeProfileFragment(
                    swipeProfiles[position], currentItemVP
                )

            findNavController().navigate(action, extras)
            viewModel.removeProfile(position)
        }

        cardAdapter =
            CardSwipeStackAdapter(swipeProfiles.toMutableList()) { viewpager, position, currentItemVP ->
                onJumpToProfileFragment(viewpager, position, currentItemVP)
            }

        cardManager = CardStackLayoutManager(context, cardStackLListener)
        cardManager.setDirections(Direction.FREEDOM)

        with(card_swipe_stack) {
            layoutManager = cardManager
            adapter = cardAdapter
            isNestedScrollingEnabled = true

//            addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
//                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
//
//                }
//
//                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
//
//                    when (e.action) {
//                        MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
//                    }
//                    return false
//                }
//
//                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
//                }
//
//            })
        }

    }

    private fun setUpObserver() {
        viewModel.result.observe(viewLifecycleOwner, Observer {
            Timber.d( "observe")
            when (it) {
                is ResultProfile.Waiting -> {
                    Timber.d("Waiting")
                    prg_loading.visibility = View.VISIBLE
                }

                is ResultProfile.Success -> {
                    Timber.d("Success - ${it.swipeProfiles.size}")
                    prg_loading.visibility = View.GONE
                    swipeProfiles = it.swipeProfiles

                    tv_nodata.visibility = if (swipeProfiles.isEmpty()) View.VISIBLE else  View.GONE

                    setUpCardStackView()
                }

                is ResultProfile.Failure -> {
                    prg_loading.visibility = View.GONE
                    Timber.d("Failure")
                }
            }
        })
    }
}