package phu.nguyen.dateme.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import phu.nguyen.dateme.data.AuthenticationRepository
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(
            authenticationRepository
        ) as T
    }
}