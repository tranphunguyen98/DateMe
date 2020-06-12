package phu.nguyen.dateme.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import phu.nguyen.dateme.data.ProfileRepository
import phu.nguyen.dateme.remote.mapper.NetworkProfileMapper
import javax.inject.Inject

class DashboardViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    @Inject
    lateinit var networkProfileMapper: NetworkProfileMapper

    val text: LiveData<String> = _text
    fun getData() {
        viewModelScope.launch {
            Log.d("testCoroutine", Thread.currentThread().name)
            Log.d("testRemote", "DashboardViewModel")
            val profiles = profileRepository.getProfiles()
            Log.d("testRemote1", profiles.size.toString())
            Log.d("testRemote1", profiles[0].images.size.toString())
        }

    }

}