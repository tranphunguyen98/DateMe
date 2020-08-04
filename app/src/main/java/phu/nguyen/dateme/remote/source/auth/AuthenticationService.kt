package phu.nguyen.dateme.remote.source.auth

import android.content.Context
import android.content.Intent
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
import timber.log.Timber
import java.io.IOException
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

    fun isFirstTimeLogin(): Boolean {
        Timber.d("Check First Time")
        val metadata = firebaseAuth.currentUser?.metadata
            ?: throw IOException("User doesn't exist metadata!")

        Timber.d("${metadata.creationTimestamp} - ${metadata.lastSignInTimestamp}")

        metadata.let {
            return  metadata.lastSignInTimestamp - metadata.creationTimestamp <= 5
        }

    }

    fun wasLogged(): String? {
        return firebaseAuth.currentUser?.uid
        //val signInAccount = GoogleSignIn.getLastSignedInAccount(context)
        //return signInAccount != null || firebaseAuth.currentUser != null
    }

    fun getSignInIntent(): Intent = signInClient.signInIntent

    suspend fun signInWithGoogle(intent: Intent): NetworkUser =
        withContext(Dispatchers.IO) {
            val signInTask =
                GoogleSignIn.getSignedInAccountFromIntent(intent)

            try {
                val signInAcc =
                    signInTask.getResult(ApiException::class.java)
                val authCredential =
                    GoogleAuthProvider.getCredential(signInAcc!!.idToken, null)

                val authResult = firebaseAuth.signInWithCredential(authCredential).await()
                Timber.d(authResult.user.toString())
                Timber.d(authResult.user?.displayName ?: "null")
                Timber.d(authResult.user?.uid ?: "null")
                Timber.d(authResult?.additionalUserInfo?.profile.toString())
                return@withContext NetworkUser(uid = authResult.user?.uid!! ,name = authResult.user?.displayName ?: "")

            } catch (e: Exception) {
                Timber.d(e.message ?: "null")
            }

            return@withContext NetworkUser()
        }

    fun logout() {
        firebaseAuth.signOut()
    }

}