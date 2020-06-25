package phu.nguyen.dateme.data.repository.authentication

interface AuthenticationDataSource {
    suspend fun signInWithGoogle()
    suspend fun logout()
}