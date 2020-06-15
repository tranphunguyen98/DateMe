package phu.nguyen.dateme.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import phu.nguyen.dateme.R

class ProfileFragment : Fragment() {

    private lateinit var homeViewModel: ChatViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(ChatViewModel::class.java)
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }
}