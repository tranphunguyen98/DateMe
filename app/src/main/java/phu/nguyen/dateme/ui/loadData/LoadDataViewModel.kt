package phu.nguyen.dateme.ui.loadData

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import phu.nguyen.dateme.data.UserRepository
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.ui.login.data.Result

class LoadDataViewModel(private val userRepository: UserRepository) : ViewModel() {

    private var _result: MutableLiveData<Result<User>> = MutableLiveData<Result<User>>()
    val result: LiveData<Result<User>>
        get() = _result

    fun getData(uid: String) {
        Log.d("testRemoteHome", "HomeViewModel")
        _result.value = Result.Waiting
        viewModelScope.launch {
            try {
                _result.value =
                    Result.Success(userRepository.getUser(uid))
                Log.d("testRemoteHome", _result.value.toString())
            } catch (e: Exception) {
                _result.value = Result.Error(e)
            }
        }

    }

}