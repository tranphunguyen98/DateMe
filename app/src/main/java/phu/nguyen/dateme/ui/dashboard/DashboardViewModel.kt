package phu.nguyen.dateme.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import phu.nguyen.dateme.common.ResultProfile
import phu.nguyen.dateme.data.MatchingRepository
import phu.nguyen.dateme.data.ProfileRepository
import phu.nguyen.dateme.data.UserRepository
import phu.nguyen.dateme.data.chat.ChatRepository
import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.data.model.UserBasicInfo
import phu.nguyen.dateme.remote.mapper.NetworkProfileMapper
import timber.log.Timber
import javax.inject.Inject

class DashboardViewModel(
    private val profileRepository: ProfileRepository,
    private val matchingRepository: MatchingRepository,
    private val userRepository: UserRepository,
    private val chatRepository: ChatRepository
) : ViewModel() {

    private var _result: MutableLiveData<ResultProfile> = MutableLiveData()
    val result: LiveData<ResultProfile>
        get() = _result

    private var _userMatching: MutableLiveData<UserBasicInfo> = MutableLiveData()
    val matchingUser: LiveData<UserBasicInfo>
        get() = _userMatching

    init {
        getData()
    }

    @Inject
    lateinit var networkProfileMapper: NetworkProfileMapper

    fun removeProfile(position: Int) {
        if (_result.value is ResultProfile.Success && position > 0) {
            Timber.d(
                (_result.value as ResultProfile.Success).profiles.size.toString()
            )
            for (i in 0 until position) {
                (_result.value as ResultProfile.Success).profiles.removeAt(0)
            }
            Timber.d(
                (_result.value as ResultProfile.Success).profiles.size.toString()
            )
        }
    }

    fun saveMatching(interaction: Interaction) {
        viewModelScope.launch {
            try {
                if ((interaction.typeSwipe == Interaction.LIKE || interaction.typeSwipe == Interaction.SUPER_LIKE)
                    && matchingRepository.checkMatching(interaction.uid)
                ) {
                    Timber.d("TestCheckMatching1")
                    matchingRepository.saveMatching(interaction.copy(match = true))
                    Timber.d("TestCheckMatching2")
                    chatRepository.saveFirstChat(interaction)
                    Timber.d("TestCheckMatching3")
                    _userMatching.value = userRepository.getUser(interaction.uid).userBasicInfo
                    Timber.d("TestCheckMatching4")
                } else {
                    matchingRepository.saveMatching(interaction)
                }
            } catch (e: Exception) {
                _result.value = ResultProfile.Failure(e)
            }
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