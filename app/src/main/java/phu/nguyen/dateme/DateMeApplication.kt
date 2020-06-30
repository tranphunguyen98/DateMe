package phu.nguyen.dateme

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import org.jetbrains.annotations.NotNull
import timber.log.Timber

@HiltAndroidApp
class DateMeApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    inner class DebugTree : Timber.DebugTree() {
        override fun createStackElementTag(@NotNull element: StackTraceElement): String? {
            return String.format(
                "helloDateMe:%s:%s",
                super.createStackElementTag(element),
                element.lineNumber
            )
        }
    }
}