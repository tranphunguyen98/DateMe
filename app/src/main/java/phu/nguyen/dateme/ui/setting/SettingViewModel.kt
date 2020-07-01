package phu.nguyen.dateme.ui.setting

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import phu.nguyen.dateme.data.UserRepository
import phu.nguyen.dateme.data.model.Setting
import timber.log.Timber
import javax.inject.Inject

class SettingViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository
) : ViewModel() {
    private var _setting: MutableLiveData<Setting> = MutableLiveData<Setting>()
    val setting: LiveData<Setting>
        get() = _setting

    fun setSetting(setting: Setting) {
        _setting.value = setting
    }

    fun setIsGlobal(isChecked: Boolean) {
        _setting.value = _setting.value!!.copy(isGlobal = isChecked)
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
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SettingViewModel(
            userRepository
        ) as T
    }
}