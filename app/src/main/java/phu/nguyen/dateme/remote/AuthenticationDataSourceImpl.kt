package phu.nguyen.dateme.remote

import phu.nguyen.dateme.data.repository.authentication.AuthenticationDataSource

class AuthenticationDataSourceImpl(
    private val authenticationService: AuthenticationService
) : AuthenticationDataSource {
    override suspend fun signInWithGoogle() {
//        authenticationService.signInWithGoogle()
    }

    override suspend fun logout() {
//        authenticationService.logout()
    }

}