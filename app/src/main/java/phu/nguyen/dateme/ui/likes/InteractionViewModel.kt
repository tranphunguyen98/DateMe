package phu.nguyen.dateme.ui.likes

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import phu.nguyen.dateme.common.ResultProfile
import phu.nguyen.dateme.data.ProfileRepository
import timber.log.Timber

class InteractionViewModel(
    private val profileRepository: ProfileRepository,
    private val interactiveType: Int
) : ViewModel() {
    private var _result: MutableLiveData<ResultProfile> = MutableLiveData()
    val result: LiveData<ResultProfile>
        get() = _result

    init {
        getData()
    }

    private fun getData() {
        Timber.d("type: $interactiveType")
        _result.value = ResultProfile.Waiting
        viewModelScope.launch {
            try {
                _result.value =
                    ResultProfile.Success(profileRepository.getInteractiveProfiles(interactiveType).toMutableList())
                Timber.d(_result.value.toString())
            } catch (e: Exception) {
                _result.value = ResultProfile.Failure(e)
            }
        }

    }
}

class InteractionViewModelFactory(
    private val profileRepository: ProfileRepository,
    private val interactiveType: Int
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return InteractionViewModel(
            profileRepository,
            interactiveType
        ) as T
    }
}