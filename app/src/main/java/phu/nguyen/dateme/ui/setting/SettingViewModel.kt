package phu.nguyen.dateme.ui.setting

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import phu.nguyen.dateme.common.ResultCompletable
import phu.nguyen.dateme.data.UserRepository
import phu.nguyen.dateme.data.mapper.UserMapper
import phu.nguyen.dateme.data.model.Setting
import phu.nguyen.dateme.data.model.User
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject

class SettingViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : ViewModel() {
    private var _setting: MutableLiveData<Setting> = MutableLiveData()
    val setting: LiveData<Setting>
        get() = _setting

    private var _result: MutableLiveData<ResultCompletable> = MutableLiveData()
    val result: LiveData<ResultCompletable>
        get() = _result

    fun saveUser(user: User) {
        _result.value = ResultCompletable.Waiting
        viewModelScope.launch {
            try {
                userRepository.saveUser(userMapper.mapFromUI(user.copy(setting = _setting.value!!)))
                _result.value = ResultCompletable.Success
            } catch (e: IOException) {
                _result.value = ResultCompletable.Error(e)
            }
        }
    }

    fun setSetting(setting: Setting) {
        _setting.value = setting
    }

    fun setIsGlobal(isChecked: Boolean) {
        _setting.value = _setting.value!!.copy(showGlobal = isChecked)
    }

    fun setShowMe(isChecked: Boolean) {
        Timber.d("setShowMe $isChecked")
        _setting.value = _setting.value!!.copy(showMe = isChecked)
    }

    fun setShowNotification(isChecked: Boolean) {
        _setting.value = _setting.value!!.copy(showNotification = isChecked)
    }

    fun setDisplayGenderObject(displayGenderObject: Int) {
        _setting.value = _setting.value!!.copy(displayGenderObject = displayGenderObject)
    }

    fun setDisplayRangeAge(leftValue: Float, rightValue: Float) {
        _setting.value = _setting.value!!.copy(
            displayRangeAgeMin = leftValue,
            displayRangeAgeMax = rightValue
        )
    }

    fun setRangeLocation(leftValue: Float) {
        _setting.value = _setting.value!!.copy(
            displayRangeLocation = leftValue
        )
    }

}

@Suppress("UNCHECKED_CAST")
class SettingViewModelFactory @Inject constructor(
    private val userRepository: UserRepository,
    private val userMapper: UserMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingViewModel(
            userRepository,
            userMapper
        ) as T
    }
}