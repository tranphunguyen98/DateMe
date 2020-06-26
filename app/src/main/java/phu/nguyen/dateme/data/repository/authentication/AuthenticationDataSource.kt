package phu.nguyen.dateme.data.repository.authentication

import android.content.Intent
import phu.nguyen.dateme.data.model.User

interface AuthenticationDataSource {
    fun getIntentSignIn(): Intent
    suspend fun signInWithGoogle(intent: Intent) : User
    fun wasLogged(): Boolean
    fun logout()
}