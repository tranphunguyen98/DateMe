package phu.nguyen.dateme.ui.setting

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.jaygoo.widget.OnRangeChangedListener
import com.jaygoo.widget.RangeSeekBar
import dagger.hilt.android.AndroidEntryPoint
import phu.nguyen.dateme.R
import phu.nguyen.dateme.common.ResultCompletable
import phu.nguyen.dateme.common.snack
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.databinding.FragmentSettingBinding
import phu.nguyen.dateme.ui.main.HomeActivity
import timber.log.Timber
import javax.inject.Inject


@AndroidEntryPoint
class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    @Inject
    lateinit var factory: SettingViewModelFactory

    private lateinit var viewModel: SettingViewModel
    private lateinit var binding: FragmentSettingBinding
    private lateinit var user: User
    private lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this, factory).get(SettingViewModel::class.java)
        binding = FragmentSettingBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        user = (activity as HomeActivity).user
        viewModel.setSetting(user.setting)
        binding.viewModel = viewModel

        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    viewModel.saveUser(user)
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        setUpUI()
        setUpObserver()
    }

    private fun setUpObserver() {

        viewModel.result.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is ResultCompletable.Waiting -> {
                    Timber.d("save Waiting...")
                    progressDialog.show()
                }
                is ResultCompletable.Success -> {
                    Timber.d("save Success")
                    progressDialog.cancel()
                    NavHostFragment.findNavController(this).popBackStack()
                    (activity as HomeActivity).user = user.copy(setting = viewModel.setting.value!!)
                }
                is ResultCompletable.Error -> {
                    (activity as AppCompatActivity).snack(result.exception.message!!)
                    progressDialog.cancel()
                }
            }
        })
    }

    private fun setUpUI() {

        progressDialog = ProgressDialog(context).apply {
            setTitle("Waiting...")
        }

        binding.rsbAge.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {
                Timber.d("onStartTrackingTouch")
            }

            override fun onRangeChanged(
                view: RangeSeekBar?,
                leftValue: Float,
                rightValue: Float,
                isFromUser: Boolean
            ) {
                Timber.d("$leftValue - $rightValue - $isFromUser")
                if (isFromUser) {
                    viewModel.setDisplayRangeAge(leftValue, rightValue)
                }
            }

            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {
                Timber.d("onStopTrackingTouch")
            }

        })

        binding.rsbRangeLocation.setOnRangeChangedListener(object : OnRangeChangedListener {
            override fun onStartTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {}

            override fun onRangeChanged(
                view: RangeSeekBar?,
                leftValue: Float,
                rightValue: Float,
                isFromUser: Boolean
            ) {
                Timber.d("location: $leftValue - $rightValue - $isFromUser")
                if (isFromUser) {
                    viewModel.setRangeLocation(leftValue)
                }
            }

            override fun onStopTrackingTouch(view: RangeSeekBar?, isLeft: Boolean) {}

        })

        binding.radioButtonGender.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_male -> viewModel.setDisplayGenderObject(0)
                R.id.rb_female -> viewModel.setDisplayGenderObject(1)
                R.id.rb_all -> viewModel.setDisplayGenderObject(2)
            }
        }

        binding.switchGlobal.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setIsGlobal(isChecked)
        }

        binding.switchShowMe.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setShowMe(isChecked)
        }

        binding.switchShowNotification.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setShowNotification(isChecked)
        }

    }
}