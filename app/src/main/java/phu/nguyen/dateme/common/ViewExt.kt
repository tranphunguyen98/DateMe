package phu.nguyen.dateme.common

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    Snackbar.make(this, message, length).apply {
        f()
        show()
    }
}

inline fun AppCompatActivity.snack(
    message: String,
    length: Int = Snackbar.LENGTH_LONG,
    f: Snackbar.() -> Unit = {}
) {
    Snackbar.make(findViewById(android.R.id.content), message, length).apply {
        f()
        show()
    }
}

inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    startActivity(intent,options)
}

inline fun <reified T:Any> newIntent(context: Context): Intent =
    Intent(context,T::class.java)
