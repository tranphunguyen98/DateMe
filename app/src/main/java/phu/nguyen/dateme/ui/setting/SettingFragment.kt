package phu.nguyen.dateme.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.fragment_setting.*
import phu.nguyen.dateme.R

class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    private lateinit var viewModel: SettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(SettingViewModel::class.java)
        // TODO: Use the ViewModel

        radio_button_gender.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rb_male -> {
                    rb_male.setTextColor(resources.getColor(R.color.white))
                    rb_female.setTextColor(resources.getColor(R.color.colorPrimary))
                    rb_all.setTextColor(resources.getColor(R.color.colorPrimary))
                }
                R.id.rb_female -> {
                    rb_male.setTextColor(resources.getColor(R.color.colorPrimary))
                    rb_female.setTextColor(resources.getColor(R.color.white))
                    rb_all.setTextColor(resources.getColor(R.color.colorPrimary))
                }
                R.id.rb_all -> {
                    rb_male.setTextColor(resources.getColor(R.color.colorPrimary))
                    rb_female.setTextColor(resources.getColor(R.color.colorPrimary))
                    rb_all.setTextColor(resources.getColor(R.color.white))
                }
            }
        }
    }

}