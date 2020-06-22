package phu.nguyen.dateme.ui.explore

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import phu.nguyen.dateme.common.ResultProfile
import phu.nguyen.dateme.data.ProfileRepository

class ExploreViewModel(private val profileRepository: ProfileRepository) : ViewModel() {
    private var _result: MutableLiveData<ResultProfile> = MutableLiveData<ResultProfile>()
    val result: LiveData<ResultProfile>
        get() = _result
    init {
        getData()
    }

    private fun getData() {
        _result.value = ResultProfile.Waiting
        viewModelScope.launch {
            try {
                _result.value =
                    ResultProfile.Success(profileRepository.getProfiles().toMutableList())
                Log.d("testObserver1", _result.value.toString())
            } catch (e: Exception) {
                _result.value = ResultProfile.Failure(e)
            }
        }

    }


}