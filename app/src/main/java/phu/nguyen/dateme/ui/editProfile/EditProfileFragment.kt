package phu.nguyen.dateme.ui.editProfile

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import phu.nguyen.dateme.R
import phu.nguyen.dateme.common.Result
import phu.nguyen.dateme.common.ResultCompletable
import phu.nguyen.dateme.common.snack
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.databinding.FragmentEditProfileBinding
import phu.nguyen.dateme.ui.main.HomeActivity
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class EditProfileFragment : Fragment() {

    companion object {
        const val REQUEST_LOAD_IMAGE = 0
    }

    @Inject
    lateinit var factory: EditProfileViewModelFactory

    private lateinit var viewModel: EditProfileViewModel
    private lateinit var binding: FragmentEditProfileBinding
    private lateinit var user: User
    private lateinit var progressDialog: ProgressDialog
    private lateinit var adapterImage: ImageEditProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        user = (activity as HomeActivity).user
        viewModel = ViewModelProvider(this,factory).get(EditProfileViewModel::class.java)
        viewModel.setMyProfile(user.myProfile)
        binding.viewModel = viewModel

        setUpCallBack()
        setUpUI()
        setUpObserver()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_LOAD_IMAGE && resultCode == Activity.RESULT_OK ) {
            Timber.d("url $")
            data?.data?.let {uri : Uri ->
               val cR = requireActivity().contentResolver
                val mime: MimeTypeMap = MimeTypeMap.getSingleton()
                val type = mime.getExtensionFromMimeType(cR.getType(uri))
                viewModel.uploadImage(type ?: "jpg", uri)
            }
        }
    }

    private fun setUpObserver() {

        viewModel.resultSaveProfile.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is ResultCompletable.Waiting -> {
                    Timber.d("save Waiting...")
                    progressDialog.show()
                }
                is ResultCompletable.Success -> {
                    Timber.d("save Success")
                    progressDialog.cancel()
                    NavHostFragment.findNavController(this).popBackStack()
                    (activity as HomeActivity).user = user.copy(myProfile = viewModel.myProfile)
                }
                is ResultCompletable.Error -> {
                    (activity as AppCompatActivity).snack(result.exception.message!!)
                    progressDialog.cancel()
                }
            }
        })

        viewModel.resultUploadImage.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Waiting -> {
                    adapterImage.setIsLoading(true)
                    Timber.d("save i Waiting...")
                    binding.btnAddImage.isEnabled = false
//                    progressDialog.show()
                }
                is Result.Success  -> {
                    binding.btnAddImage.isEnabled = true
                    Timber.d("save i Success")
                    adapterImage.setIsLoading(false)
                    adapterImage.addImage(result.data)
                    viewModel.addImage(result.data)
//                    progressDialog.()
//                    NavHostFragment.findNavController(this).popBackStack()
//                    (activity as HomeActivity).user = user.copy(myProfile = viewModel.myProfile)
                }
                is Result.Error  -> {
                    binding.btnAddImage.isEnabled = true
                    Timber.d("save i ${result.exception.message}")
                    adapterImage.setIsLoading(false)
                    showError(result.exception.message!!)
//                    progressDialog.cancel()
                }
            }
        })
    }

    private fun setUpCallBack() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    viewModel.saveUser(user)
                }
            }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }


    private fun setUpUI() {
        progressDialog = ProgressDialog(context).apply {
            setTitle("Waiting...")
        }

        val displayMetrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

        adapterImage = ImageEditProfileAdapter(viewModel.myProfile.images.toMutableList(), displayMetrics.heightPixels)

        binding.rcImages.apply {
            layoutManager = GridLayoutManager(context,3)
            adapter = adapterImage
        }

        binding.btnAddImage.setOnClickListener {
            pickImage()
        }

        binding.radioButtonGenderEdit.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_male -> viewModel.setGender(0)
                R.id.rb_female -> viewModel.setGender(1)
                R.id.rb_all -> viewModel.setGender(2)
            }
        }

        binding.edtIntroduction.addTextChangedListener {
            it?.let {
                viewModel.setIntroduction(it.toString())
            }
        }
        binding.edtBirthday.addTextChangedListener {
            it?.let {
                viewModel.setBirthday(it.toString())
            }
        }
        binding.edtHobby.addTextChangedListener {
            it?.let {
                viewModel.setHobby(it.toString())
            }
        }
        binding.edtJob.addTextChangedListener {
            it?.let {
                viewModel.setJob(it.toString())
            }
        }
        binding.edtOrganization.addTextChangedListener {
            it?.let {
                viewModel.setOrganization(it.toString())
            }
        }
        binding.edtSchool.addTextChangedListener {
            it?.let {
                viewModel.setSchool(it.toString())
            }
        }
        binding.switchShowAge.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setShowAge(isChecked)
        }
        binding.switchShowLocation.setOnCheckedChangeListener { _, isChecked ->
            viewModel.setShowLocation(isChecked)
        }
    }

    private fun pickImage() {
        if(viewModel.myProfile.images.size < 9) {
            val i = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(i, REQUEST_LOAD_IMAGE)
        } else {
           showError("size image should be smaller than 9")
        }
    }

    private fun showError(message: String) {
        (requireActivity() as AppCompatActivity).snack(message)
    }
}