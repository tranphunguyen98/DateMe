package phu.nguyen.dateme.data

import android.content.Intent
import phu.nguyen.dateme.data.model.User

interface AuthenticationRepository {
    fun wasLogged(): Boolean
    suspend fun signInWithGoogle(intent: Intent) : User
    fun getIntentGoogle(): Intent
    fun logout()
}