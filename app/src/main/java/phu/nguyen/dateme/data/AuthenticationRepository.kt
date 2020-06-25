package phu.nguyen.dateme.data

interface AuthenticationRepository {
    suspend fun signInWithGoogle()
}