package phu.nguyen.dateme.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import phu.nguyen.dateme.data.AuthenticationRepository
import phu.nguyen.dateme.data.AuthenticationRepositoryImpl
import phu.nguyen.dateme.data.repository.authentication.AuthenticationDataSource
import phu.nguyen.dateme.remote.AuthenticationDataSourceImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class AuthenticationModule {
    @Binds
    abstract fun bindAuthenticationDataSource(
        authenticationDataSourceImpl: AuthenticationDataSourceImpl
    ): AuthenticationDataSource

    @Binds
    abstract fun bindAuthenticationRepository(
        authenticationRepositoryImpl: AuthenticationRepositoryImpl
    ): AuthenticationRepository
}