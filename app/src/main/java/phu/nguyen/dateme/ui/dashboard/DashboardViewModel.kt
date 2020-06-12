package phu.nguyen.dateme.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import phu.nguyen.dateme.common.Result
import phu.nguyen.dateme.data.ProfileRepository
import phu.nguyen.dateme.remote.mapper.NetworkProfileMapper
import javax.inject.Inject

class DashboardViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

    @Inject
    lateinit var networkProfileMapper: NetworkProfileMapper

    private val _result = MutableLiveData<Result>()
    val result: LiveData<Result>
        get() = _result

    fun getData() {
        Log.d("testRemote", "DashboardViewModel")
        _result.value = Result.Waiting
        viewModelScope.launch {
            try {

//            Log.d("testCoroutine", Thread.currentThread().name)
//            Log.d("testRemote", "DashboardViewModel")
                _result.value = Result.Success(profileRepository.getProfiles())
                Log.d("testRemote1", _result.value.toString())
            } catch (e: Exception) {
                _result.value = Result.Failure(e)
            }
        }

    }

}