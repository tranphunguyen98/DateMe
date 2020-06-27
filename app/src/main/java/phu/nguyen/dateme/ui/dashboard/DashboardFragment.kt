package phu.nguyen.dateme.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import phu.nguyen.dateme.R
import phu.nguyen.dateme.common.ResultProfile
import phu.nguyen.dateme.data.model.SwipeProfile
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    @Inject
    lateinit var factory: DashboardViewModelFactory

    private lateinit var dashboardViewModel: DashboardViewModel
    private lateinit var cardAdapter: CardSwipeStackAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this, factory).get(DashboardViewModel::class.java)

        Log.d("testObserver", "onCreateView")


        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d("testObserver", "onViewCreated")
        img_match.transitionName = "image"
        setUpObserver()
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("testObserver", "onActivityCreated")
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
//                    dashboardViewModel.removeTop()
                    Log.d("testCardSwiped", "left - ${cardAdapter.itemCount}")
                }
                Direction.Top -> {
                    Log.d("testCardSwiped", "Top")

                }
                Direction.Bottom -> {
                    Log.d("testCardSwiped", "Bottom")

                }
                Direction.Right -> {
//                    dashboardViewModel.removeTop()
                    Log.d("testCardSwiped", "Right")
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

    private fun setUpCardStackView(swipeProfiles: List<SwipeProfile>) {

        cardAdapter = CardSwipeStackAdapter(swipeProfiles.toMutableList()) { viewpager, position, currentItemVP ->
            Log.d("testCard", "$position - ${swipeProfiles.size}")
            val extras = FragmentNavigatorExtras(
                viewpager to "profile${swipeProfiles[position].id}",
                img_match to "image"
            )
            val action =
                DashboardFragmentDirections.actionNavigationDashboardToSwipeProfileFragment(
                    swipeProfiles[position], currentItemVP
                )

            findNavController().navigate(action, extras)
            dashboardViewModel.remove(position)
        }

        val cardManager = CardStackLayoutManager(context, cardStackLListener)
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
        dashboardViewModel.result.observe(viewLifecycleOwner, Observer {
            Log.d("testObserver", "observe")
            when (it) {
                is ResultProfile.Waiting -> {
                    Log.d("testObserver", "Waiting")
                    prg_loading.visibility = View.VISIBLE
                }
                is ResultProfile.Success -> {
                    Log.d("testObserver", "Success - ${it.swipeProfiles.size}")
                    prg_loading.visibility = View.GONE
                    setUpCardStackView(it.swipeProfiles)
                }
                is ResultProfile.Failure -> {
                    prg_loading.visibility = View.GONE
                    Log.d("testObserver", "Failure")
                }
            }
        })
    }
}