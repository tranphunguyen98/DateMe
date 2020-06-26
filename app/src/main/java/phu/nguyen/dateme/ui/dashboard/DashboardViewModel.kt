package phu.nguyen.dateme.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import phu.nguyen.dateme.common.ResultProfile
import phu.nguyen.dateme.data.ProfileRepository
import phu.nguyen.dateme.remote.mapper.NetworkProfileMapper
import javax.inject.Inject

class DashboardViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

    private var _result: MutableLiveData<ResultProfile> = MutableLiveData<ResultProfile>()
    val result: LiveData<ResultProfile>
        get() = _result

    init {
//        Log.d("testObserve", "DashboardViewModel created")
        getData()
    }

    @Inject
    lateinit var networkProfileMapper: NetworkProfileMapper




    fun remove(position : Int) {
        if (_result.value is ResultProfile.Success && position > 0) {
            Log.d("testCard", (_result.value as ResultProfile.Success).profiles.size.toString())
            for(i in 0 until position) {
                (_result.value as ResultProfile.Success).profiles.removeAt(0)
            }
            Log.d("testCard", (_result.value as ResultProfile.Success).profiles.size.toString())
        }
    }

    fun getData() {
//        Log.d("testRemote", "DashboardViewModel")
        _result.value = ResultProfile.Waiting
        viewModelScope.launch {
            try {

//            Log.d("testCoroutine", Thread.currentThread().name)
//            Log.d("testRemote", "DashboardViewModel")
                _result.value =
                    ResultProfile.Success(profileRepository.getProfiles().toMutableList())
                Log.d("testRemote1", _result.value.toString())
            } catch (e: Exception) {
                _result.value = ResultProfile.Failure(e)
            }
        }

    }

}