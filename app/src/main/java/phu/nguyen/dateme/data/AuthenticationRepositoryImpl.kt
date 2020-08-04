package phu.nguyen.dateme.data

import android.content.Intent
import phu.nguyen.dateme.data.repository.authentication.AuthenticationDataSource
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val authenticationDataSource: AuthenticationDataSource): AuthenticationRepository{
    override fun wasLogged() =
        authenticationDataSource.wasLogged()

    override fun isFirstTimeLogIn(): Boolean = authenticationDataSource.isFirstTimeLogIn()

    override suspend fun signInWithGoogle(intent: Intent) =
        authenticationDataSource.signInWithGoogle(intent)

    override fun getIntentGoogle(): Intent = authenticationDataSource.getIntentSignIn()

    override fun logout() {
       authenticationDataSource.logout()
    }

}