package phu.nguyen.dateme.di.module

import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class GoogleModule {

    @Provides
    fun provideGoogleSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        )
            .requestIdToken("750529344565-b2dtqenfvnb3ra71s7nc3kbpj8qm9h92.apps.googleusercontent.com")
            .requestEmail()
            .build()
    }

}