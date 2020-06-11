package phu.nguyen.dateme.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import phu.nguyen.dateme.data.ProfileRepository
import javax.inject.Inject

class DashboardViewModelFactory @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DashboardViewModel(
            profileRepository
        ) as T
    }
}