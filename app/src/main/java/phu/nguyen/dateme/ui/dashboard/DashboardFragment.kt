package phu.nguyen.dateme.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.Direction
import kotlinx.android.synthetic.main.fragment_dashboard.*
import phu.nguyen.dateme.R

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    private val listImageSwipe = arrayListOf(
        R.drawable.image_avatar1,R.drawable.image_avatar2,
        R.drawable.image_avatar1,R.drawable.image_avatar2,
        R.drawable.image_avatar1,R.drawable.image_avatar2,
        R.drawable.image_avatar1,R.drawable.image_avatar2,
        R.drawable.image_avatar1,R.drawable.image_avatar2)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)

        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        setUpCardStackView(view)

        dashboardViewModel.getData()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpCardStackView(view: View) {
        val cardManager = CardStackLayoutManager(view.context)
        cardManager.setDirections(Direction.FREEDOM)

        with(card_swipe_stack) {
            layoutManager = cardManager
            adapter = CardSwipeStackAdapter(listImageSwipe) {viewpager, position ->
                val extras = FragmentNavigatorExtras(
                    viewpager to  "viewpager_dashboard$position"
                )
                val action = DashboardFragmentDirections.actionNavigationDashboardToSwipeProfileFragment("viewpager_dashboard$position")

                findNavController().navigate(action, extras)
            }
            isNestedScrollingEnabled = true
            addOnItemTouchListener(object: RecyclerView.OnItemTouchListener {
                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
                }

                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

                    when(e.action) {
                        MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                    }
                    return false
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                }

            })
        }

    }
}