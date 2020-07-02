package phu.nguyen.dateme.remote.source.auth

import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import phu.nguyen.dateme.remote.model.NetworkUser
import javax.inject.Inject

class AuthenticationService @Inject constructor(
    @ActivityContext private val context: Context,
    private val gsc: GoogleSignInOptions
) {
    private val firebaseAuth by lazy {
        FirebaseAuth.getInstance()
    }

    private val signInClient by lazy {
        GoogleSignIn.getClient(context, gsc)
    }

    fun wasLogged(): Boolean {
        val signInAccount = GoogleSignIn.getLastSignedInAccount(context)
        return signInAccount != null || firebaseAuth.currentUser != null
    }

    fun getSignInIntent(): Intent = signInClient.signInIntent

    suspend fun signInWithGoogle(intent: Intent):NetworkUser =
        withContext(Dispatchers.IO) {
            val signInTask =
                GoogleSignIn.getSignedInAccountFromIntent(intent)

            try {
                val signInAcc =
                    signInTask.getResult(ApiException::class.java)
                val authCredential =
                    GoogleAuthProvider.getCredential(signInAcc!!.idToken, null)

                val authResult = firebaseAuth.signInWithCredential(authCredential).await()
                Log.d("TestAuth", authResult.user.toString())
                Log.d("TestAuth", authResult.user?.displayName ?: "null")
                Log.d("TestAuth", authResult.user?.uid ?: "null")
                Log.d("TestAuth",  authResult?.additionalUserInfo?.profile.toString())
                return@withContext NetworkUser(name = authResult.user?.displayName ?: "")

            } catch (e: ApiException) {
                Log.d("TestAuth", e.message ?: "null")
            }
            return@withContext NetworkUser()
        }

    fun logout() {
        firebaseAuth.signOut()
    }

}