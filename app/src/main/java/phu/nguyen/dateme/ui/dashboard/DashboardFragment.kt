package phu.nguyen.dateme.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
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
        activity?.actionBar?.hide()

        super.onViewCreated(view, savedInstanceState)
    }

    private fun setUpCardStackView(view: View) {

        with(card_swipe_stack) {
            layoutManager = CardStackLayoutManager(activity)
            adapter = CardSwipeStackAdapter(listImageSwipe)
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