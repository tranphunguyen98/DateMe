package phu.nguyen.dateme.ui.loadData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import phu.nguyen.dateme.data.UserRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class LoadDataViewModelFactory @Inject constructor(
    private val userRepository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoadDataViewModel(
            userRepository
        ) as T
    }
}