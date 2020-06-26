package phu.nguyen.dateme.remote

import android.content.Intent
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.data.repository.authentication.AuthenticationDataSource
import phu.nguyen.dateme.remote.mapper.NetworkUserMapper
import javax.inject.Inject

class AuthenticationDataSourceImpl @Inject constructor(
    private val authenticationService: AuthenticationService,
    private val networkUserMapper: NetworkUserMapper
) : AuthenticationDataSource {

    override fun logout() {
        authenticationService.logout()
    }

    override fun getIntentSignIn(): Intent =
        authenticationService.getSignInIntent()


    override suspend fun signInWithGoogle(intent: Intent): User =
        networkUserMapper.mapFromRemote(authenticationService.signInWithGoogle(intent))

    override fun wasLogged(): Boolean = authenticationService.wasLogged()

}