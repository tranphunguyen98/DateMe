package phu.nguyen.dateme.ui.loadData

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.animation.BounceInterpolator
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import phu.nguyen.dateme.R
import phu.nguyen.dateme.databinding.ActivityLoadDataBinding
import phu.nguyen.dateme.ui.login.data.Result
import phu.nguyen.dateme.ui.main.HomeActivity
import javax.inject.Inject

@AndroidEntryPoint
class LoadDataActivity : AppCompatActivity() {
    @Inject
    lateinit var factory: LoadDataViewModelFactory

    private lateinit var viewModel: LoadDataViewModel
    private lateinit var binding: ActivityLoadDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_load_data)

        setUpViewModel()
        animatedLogoLoading()
        setUpObserver()
    }

    private fun setUpViewModel() {
        viewModel =
            ViewModelProvider(this, factory).get(LoadDataViewModel::class.java)

        viewModel.getData("oMTAcviWFZWEIuMv0HIbJyyIKIB2")
    }

    private fun setUpObserver() {
        viewModel.result.observe(this, Observer { result ->
            when (result) {
                is Result.Success -> {
                    Log.d("testLogin", "Success ${result.data.userBasicInfo.name}")
                    startActivity(Intent(this,HomeActivity::class.java))
                }
                is Result.Waiting -> {
                    Log.d("testLogin", "Waiting...")
                }
                is Result.Error -> {
                    Log.d("testLogin", "Error ${result.exception.message}")
                }
            }
        })
    }


    private fun animatedLogoLoading() {

        val xAnimator = ObjectAnimator.ofFloat(binding.imgLogoLoading, "scaleX", 0f, 1f).apply {
            duration = 500
        }
        val yAnimator = ObjectAnimator.ofFloat(binding.imgLogoLoading, "scaleY", 0f, 1f).apply {
            duration = 500
        }
        val alphaAnimator = ObjectAnimator.ofFloat(binding.imgLogoLoading, "alpha", 0f, 1f).apply {
            duration = 500
        }

        val xHeartbeatAnimator =
            ObjectAnimator.ofFloat(binding.imgLogoLoading, "scaleX", 1f, 0.75f).apply {
                duration = 500
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                interpolator = BounceInterpolator()
            }
        val yHeartbeatAnimator =
            ObjectAnimator.ofFloat(binding.imgLogoLoading, "scaleY", 1f, 0.75f).apply {
                duration = 500
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                interpolator = BounceInterpolator()
            }
        val alphaHeartbeatAnimator =
            ObjectAnimator.ofFloat(binding.imgLogoLoading, "alpha", 0.5f, 1f).apply {
                repeatCount = ObjectAnimator.INFINITE
                repeatMode = ObjectAnimator.REVERSE
                interpolator = BounceInterpolator()
                duration = 500
            }

        AnimatorSet().apply {
//            play(xAnimator).with(yAnimator).with(alphaAnimator).before(xHeartbeatAnimator).with(yHeartbeatAnimator)
            play(xHeartbeatAnimator).with(yHeartbeatAnimator).with(alphaHeartbeatAnimator)
            start()
        }
    }
}