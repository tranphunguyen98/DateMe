package phu.nguyen.dateme.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import phu.nguyen.dateme.data.AuthenticationRepository
import phu.nguyen.dateme.data.UserRepository
import phu.nguyen.dateme.data.mapper.UserMapper
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class LoginViewModelFactory @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    private val userRepository: UserRepository,
    private val mapper: UserMapper
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(
            authenticationRepository,
            userRepository,
            mapper
        ) as T
    }
}