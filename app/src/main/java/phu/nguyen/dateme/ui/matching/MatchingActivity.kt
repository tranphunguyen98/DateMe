package phu.nguyen.dateme.ui.matching

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import phu.nguyen.dateme.R
import phu.nguyen.dateme.data.model.UserBasicInfo
import phu.nguyen.dateme.databinding.ActivityMatchingBinding

class MatchingActivity : AppCompatActivity() {
    companion object {
        const val MY_USER_KEY = "myUser"
        const val MATCHING_USER_KEY = "userMatching"
    }

    private lateinit var binding: ActivityMatchingBinding
    private lateinit var viewModel: MatchingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matching)
        viewModel = ViewModelProvider(this).get(MatchingViewModel::class.java)

        binding = DataBindingUtil.setContentView<ActivityMatchingBinding>(this,R.layout.activity_matching)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        setData()

    }

    private fun setData() {
        val myUser = intent.extras?.get(MY_USER_KEY)
        if(myUser is UserBasicInfo? && myUser != null) {
            viewModel.setMyUser(myUser)
        }

        val matchingUser = intent.extras?.get(MATCHING_USER_KEY)
        if(matchingUser is UserBasicInfo? && matchingUser != null) {
            viewModel.setMatchingUser(matchingUser)
        }
    }
}