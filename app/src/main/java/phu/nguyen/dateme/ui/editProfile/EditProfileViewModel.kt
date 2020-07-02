package phu.nguyen.dateme.ui.editProfile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import phu.nguyen.dateme.data.UserRepository
import phu.nguyen.dateme.data.mapper.UserMapper
import phu.nguyen.dateme.data.model.MyProfile
import javax.inject.Inject

class EditProfileViewModel(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : ViewModel() {
    private var _myProfile: MyProfile = MyProfile()
    val myProfile: MyProfile
        get() = _myProfile

    fun setMyProfile(myProfile: MyProfile) {
        _myProfile = myProfile
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