package phu.nguyen.dateme.ui.editProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import phu.nguyen.dateme.R

class EditProfileFragment : Fragment() {

    companion object {
        fun newInstance() = EditProfileFragment()
    }

    private lateinit var viewModel: EditProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EditProfileViewModel::class.java)
        // TODO: Use the ViewModel
        val image1 = "https://firebasestorage.googleapis.com/v0/b/dateme-f8ef6.appspot.com/o/1_1.jpg?alt=media&token=2e656a4d-ef49-4b77-83ae-0bdf84bea3cf"
        val image2 = "https://firebasestorage.googleapis.com/v0/b/dateme-f8ef6.appspot.com/o/1_2.jpg?alt=media&token=79a6f89c-e232-4751-a617-79e3bfb3c023"

        val listImage = listOf<String>(image1,image2,image1,image2,image1,image2,image1,image2,image1)
        rc_images.layoutManager = GridLayoutManager(context,3)
        rc_images.adapter = ImageEditProfileAdapter(listImage)

        radio_button_gender_edit.setOnCheckedChangeListener { group, checkedId ->
            when(checkedId) {
                R.id.rb_male_edit -> {
                    rb_male_edit.setTextColor(resources.getColor(R.color.white))
                    rb_female_edit.setTextColor(resources.getColor(R.color.colorPrimary))
                    rb_other_edit.setTextColor(resources.getColor(R.color.colorPrimary))
                }
                R.id.rb_female_edit -> {
                    rb_male_edit.setTextColor(resources.getColor(R.color.colorPrimary))
                    rb_female_edit.setTextColor(resources.getColor(R.color.white))
                    rb_other_edit.setTextColor(resources.getColor(R.color.colorPrimary))
                }
                R.id.rb_other_edit -> {
                    rb_male_edit.setTextColor(resources.getColor(R.color.colorPrimary))
                    rb_female_edit.setTextColor(resources.getColor(R.color.colorPrimary))
                    rb_other_edit.setTextColor(resources.getColor(R.color.white))
                }
            }
        }
    }

}