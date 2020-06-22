package phu.nguyen.dateme.ui.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import phu.nguyen.dateme.data.ProfileRepository
import javax.inject.Inject

class ExploreViewModelFactory @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ExploreViewModel(
            profileRepository
        ) as T
    }
}