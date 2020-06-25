package phu.nguyen.dateme.remote

import android.content.Context
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.qualifiers.ActivityContext
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

}