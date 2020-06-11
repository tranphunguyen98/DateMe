package phu.nguyen.dateme.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import phu.nguyen.dateme.data.ProfileDataRepository
import phu.nguyen.dateme.data.source.ProfileCacheDataStore
import phu.nguyen.dateme.data.source.ProfileDataStoreFactory
import phu.nguyen.dateme.data.source.ProfileRemoteDataStore
import phu.nguyen.dateme.remote.ProfileRemoteImpl
import phu.nguyen.dateme.remote.ProfileService
import phu.nguyen.dateme.remote.mapper.NetworkProfileMapper

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }

    val text: LiveData<String> = _text
    fun getData() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("testRemote", "DashboardViewModel")
            val profiles = ProfileDataRepository(
                ProfileDataStoreFactory(
                    ProfileCacheDataStore(

                    ),
                    ProfileRemoteDataStore(
                        ProfileRemoteImpl(
                            ProfileService(),
                            NetworkProfileMapper()
                        )
                    )
                )
            ).getProfiles()
            Log.d("testRemote1", profiles.size.toString())
        }

    }

}