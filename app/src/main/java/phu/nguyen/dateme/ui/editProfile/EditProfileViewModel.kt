package phu.nguyen.dateme.ui.editProfile

import android.net.Uri
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import phu.nguyen.dateme.common.Result
import phu.nguyen.dateme.common.ResultCompletable
import phu.nguyen.dateme.data.UserRepository
import phu.nguyen.dateme.data.mapper.UserMapper
import phu.nguyen.dateme.data.model.MyProfile
import phu.nguyen.dateme.data.model.User
import java.io.IOException
import javax.inject.Inject

class EditProfileViewModel(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : ViewModel() {
    private var _myProfile: MyProfile = MyProfile()
    val myProfile: MyProfile
        get() = _myProfile

    private var _resultSaveProfile: MutableLiveData<ResultCompletable> = MutableLiveData()
    val resultSaveProfile: LiveData<ResultCompletable>
        get() = _resultSaveProfile

    private var _resultUploadImage: MutableLiveData<Result<String>> = MutableLiveData()
    val resultUploadImage: LiveData<Result<String>>
        get() = _resultUploadImage


    fun saveUser(user: User) {
        _resultSaveProfile.value = ResultCompletable.Waiting
        viewModelScope.launch {
            try {
                userRepository.saveUser(userMapper.mapFromUI(user.copy(myProfile = myProfile)))
                _resultSaveProfile.value = ResultCompletable.Success
            } catch (e: IOException) {
                _resultSaveProfile.value = ResultCompletable.Error(e)
            }
        }
    }

    fun uploadImage(type: String, uri: Uri) {
        _resultUploadImage.value = Result.Waiting
        try {
            viewModelScope.launch {
                _resultUploadImage.value = Result.Success(userRepository.uploadImage(getNameImage(type), uri))
            }
        } catch (e: Exception) {
            _resultUploadImage.value = Result.Error(e)
        }
    }

    private fun getNameImage(type: String): String {
        return "${_myProfile.uid}${_myProfile.images.size}.$type"
    }

    fun addImage(image: String) {
        val newListImage = _myProfile.images.toMutableList()
        newListImage.add(image)

        _myProfile = _myProfile.copy(images = newListImage)
    }

    fun setGender(gender: Int) {
        _myProfile = _myProfile.copy(gender = gender)
    }

    fun setMyProfile(myProfile: MyProfile) {
        _myProfile = myProfile
    }

    fun setIntroduction(introduction: String) {
        _myProfile = _myProfile.copy(introduction = introduction)
    }

    fun setSchool(school: String) {
        _myProfile = _myProfile.copy(school = school)
    }

    fun setHobby(hobby: String) {
        _myProfile = _myProfile.copy(hobby = hobby)
    }

    fun setOrganization(organization: String) {
        _myProfile = _myProfile.copy(organization = organization)
    }

    fun setBirthday(birthday: String) {
        _myProfile = _myProfile.copy(birthday = birthday)
    }

    fun setJob(job: String) {
        _myProfile = _myProfile.copy(job = job)
    }

    fun setShowAge(isCheck: Boolean) {
        _myProfile = _myProfile.copy(notShowAge = isCheck)
    }

    fun setShowLocation(isCheck: Boolean) {
        _myProfile = _myProfile.copy(notShowLocation = isCheck)
    }
}

@Suppress("UNCHECKED_CAST")
class EditProfileViewModelFactory @Inject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EditProfileViewModel(
            userRepository,
            userMapper
        ) as T
    }
}