package phu.nguyen.dateme.ui.login

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import phu.nguyen.dateme.common.Result
import phu.nguyen.dateme.data.AuthenticationRepository
import phu.nguyen.dateme.data.UserRepository
import phu.nguyen.dateme.data.mapper.UserMapper
import phu.nguyen.dateme.data.model.User
import timber.log.Timber

class LoginViewModel(private val repository: AuthenticationRepository,
                     private val userRepository: UserRepository,
                   private val mapper: UserMapper) : ViewModel() {

//    private val _loginForm = MutableLiveData<LoginFormState>()
//    val loginFormState: LiveData<LoginFormState> = _loginForm

//    private val _loginResult = MutableLiveData<LoginResult>()
//    val loginResult: LiveData<LoginResult> = _loginResult

    private var _result: MutableLiveData<Result<User>> = MutableLiveData<Result<User>>()
    val result: LiveData<Result<User>>
        get() = _result

    fun wasLogged(): String? = repository.wasLogged()

    fun getSignInIntent(): Intent = repository.getIntentGoogle()

    fun isFirstTimeLogIn(): Boolean = repository.isFirstTimeLogIn()

    fun logout() {
        repository.logout()
    }

    fun loginWithGoogle(intent: Intent) {
        _result.value = Result.Waiting
        viewModelScope.launch {
            try {
               val user = repository.signInWithGoogle(intent)

                Timber.d("check First")
                if(repository.isFirstTimeLogIn()) {
                    Timber.d("check First 1")
                    userRepository.saveUser(mapper.mapFromUI(user))
                    Timber.d("check First 2")
                }
                Timber.d("check First 3")

                _result.value = Result.Success(user)
            } catch (e: Exception){
                _result.value = Result.Error(e)
            }
        }
        // can be launched in a separate asynchronous job
        //val result = loginRepository.login(username, password)

//        if (result is Result.Success) {
//            _loginResult.value =
//                LoginResult(success = LoggedInUserView(displayName = result.data.displayName))
//        } else {
//            _loginResult.value = LoginResult(error = R.string.login_failed)
//        }
    }

//    fun loginDataChanged(username: String, password: String) {
//        if (!isUserNameValid(username)) {
//            _loginForm.value = LoginFormState(usernameError = R.string.invalid_username)
//        } else if (!isPasswordValid(password)) {
//            _loginForm.value = LoginFormState(passwordError = R.string.invalid_password)
//        } else {
//            _loginForm.value = LoginFormState(isDataValid = true)
//        }
//    }

    // A placeholder username validation check
//    private fun isUserNameValid(username: String): Boolean {
//        return if (username.contains('@')) {
//            Patterns.EMAIL_ADDRESS.matcher(username).matches()
//        } else {
//            username.isNotBlank()
//        }
//    }

    // A placeholder password validation check
//    private fun isPasswordValid(password: String): Boolean {
//        return password.length > 5
//    }
}