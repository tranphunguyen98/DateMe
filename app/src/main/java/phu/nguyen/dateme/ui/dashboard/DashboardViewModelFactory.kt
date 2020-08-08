package phu.nguyen.dateme.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import phu.nguyen.dateme.data.MatchingRepository
import phu.nguyen.dateme.data.ProfileRepository
import phu.nguyen.dateme.data.UserRepository
import javax.inject.Inject

class DashboardViewModelFactory @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val matchingRepository: MatchingRepository,
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(
            profileRepository, matchingRepository, userRepository
        ) as T
    }
}