package phu.nguyen.dateme.common

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar

inline fun View.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit) {
    Snackbar.make(this, message, length).apply {
        f()
        show()
    }
}

inline fun AppCompatActivity.snack(message: String, length: Int = Snackbar.LENGTH_LONG, f: Snackbar.() -> Unit = {}) {
    Snackbar.make(findViewById(android.R.id.content), message, length).apply {
        f()
        show()
    }
}
