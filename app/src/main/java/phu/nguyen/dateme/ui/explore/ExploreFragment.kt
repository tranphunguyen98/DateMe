package phu.nguyen.dateme.ui.explore

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import phu.nguyen.dateme.R
import phu.nguyen.dateme.ui.dashboard.DashboardViewModel

class ExploreFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProviders.of(this).get(DashboardViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_explore, container, false)
        val textView: TextView = root.findViewById(R.id.text_explore)
        return root
    }
}