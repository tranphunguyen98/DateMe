package phu.nguyen.dateme.ui.login

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.SignInButton
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import phu.nguyen.dateme.R
import phu.nguyen.dateme.ui.main.HomeActivity


class LoginActivity : AppCompatActivity() {
    val GOOGLE_SIGN_IN_CODE = 10005
    lateinit var signIn: SignInButton
    lateinit var gso: GoogleSignInOptions
    lateinit var signInClient: GoogleSignInClient
    lateinit var firebaseAuth: FirebaseAuth
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)
        val login = findViewById<Button>(R.id.login)
        val loading = findViewById<ProgressBar>(R.id.loading)
        val signIn = findViewById<SignInButton>(R.id.btn_sign_in_with_google)


        firebaseAuth = FirebaseAuth.getInstance()

        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("750529344565-b2dtqenfvnb3ra71s7nc3kbpj8qm9h92.apps.googleusercontent.com")
            .requestEmail()
            .build()

        signInClient = GoogleSignIn.getClient(this, gso)

        val signInAccount = GoogleSignIn.getLastSignedInAccount(this)

        if (signInAccount != null || firebaseAuth.currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
        }

        signIn.setOnClickListener {
            val sign = signInClient.signInIntent
            startActivityForResult(sign, GOOGLE_SIGN_IN_CODE)
        }




        loginViewModel = ViewModelProviders.of(this, LoginViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })

        loginViewModel.loginResult.observe(this@LoginActivity, Observer {
            val loginResult = it ?: return@Observer

            loading.visibility = View.GONE
            if (loginResult.error != null) {
                showLoginFailed(loginResult.error)
            }
            if (loginResult.success != null) {
                updateUiWithUser(loginResult.success)
            }
            setResult(Activity.RESULT_OK)

            //Complete and destroy login activity once successful
            finish()
        })

        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            setOnEditorActionListener { _, actionId, _ ->
                when (actionId) {
                    EditorInfo.IME_ACTION_DONE ->
                        loginViewModel.login(
                            username.text.toString(),
                            password.text.toString()
                        )
                }
                false
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString())
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GOOGLE_SIGN_IN_CODE) {
            val signInTask =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val signInAcc =
                    signInTask.getResult(ApiException::class.java)
                val authCredential =
                    GoogleAuthProvider.getCredential(signInAcc!!.idToken, null)

                firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener {
                    Toast.makeText(
                        applicationContext,
                        "Your Google Account is Connected to Our Application.",
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(applicationContext, HomeActivity::class.java))
                }.addOnFailureListener {
                    Toast.makeText(
                        applicationContext,
                        it.message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            }
        }
    }

    private fun updateUiWithUser(model: LoggedInUserView) {
        val welcome = getString(R.string.welcome)
        val displayName = model.displayName
        // TODO : initiate successful logged in experience
        Toast.makeText(
            applicationContext,
            "$welcome $displayName",
            Toast.LENGTH_LONG
        ).show()
    }

    private fun showLoginFailed(@StringRes errorString: Int) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}


/**
 * Extension function to simplify setting an afterTextChanged action to EditText components.
 */
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}