package phu.nguyen.dateme.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.Direction
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_dashboard.*
import phu.nguyen.dateme.R
import phu.nguyen.dateme.common.Result
import phu.nguyen.dateme.data.model.Profile
import javax.inject.Inject

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    @Inject
    lateinit var factory: DashboardViewModelFactory

    private lateinit var dashboardViewModel: DashboardViewModel

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
        Log.d("testObserver", "onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("testObserver", "onDetach")
    }

    private fun setUpCardStackView(profiles: List<Profile>) {
        val cardManager = CardStackLayoutManager(context)
        cardManager.setDirections(Direction.FREEDOM)

        with(card_swipe_stack) {
            layoutManager = cardManager
            adapter = CardSwipeStackAdapter(profiles) { viewpager, position ->
                val extras = FragmentNavigatorExtras(
                    viewpager to "profile${profiles[position].id}",
                    img_match to "image"
                )
                val action =
                    DashboardFragmentDirections.actionNavigationDashboardToSwipeProfileFragment(
                        profiles[position]
                    )

                findNavController().navigate(action, extras)
            }

            isNestedScrollingEnabled = true

            addOnItemTouchListener(object : RecyclerView.OnItemTouchListener {
                override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

                }

                override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {

                    when (e.action) {
                        MotionEvent.ACTION_MOVE -> rv.parent.requestDisallowInterceptTouchEvent(true)
                    }
                    return false
                }

                override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
                }

            })
        }

    }

    private fun setUpObserver() {
        dashboardViewModel.result.observe(viewLifecycleOwner, Observer {
            Log.d("testObserver", "observe")
            when (it) {
                is Result.Waiting -> {
                    Log.d("testObserver", "Waiting")
                    prg_loading.visibility = View.VISIBLE
                }
                is Result.Success<*> -> {
                    prg_loading.visibility = View.GONE
                    if (it.value is List<*>) {
                        setUpCardStackView(it.value as List<Profile>)
                        Log.d("testObserver", (it.value[0] as Profile).name)
                    }
                }
                is Result.Failure -> {
                    prg_loading.visibility = View.GONE
                    Log.d("testObserver", "Failure")
                }
            }
        })
    }
}