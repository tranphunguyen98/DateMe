package phu.nguyen.dateme.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import phu.nguyen.dateme.cache.UserCacheDataSourceImpl
import phu.nguyen.dateme.data.UserRepository
import phu.nguyen.dateme.data.UserRepositoryImpl
import phu.nguyen.dateme.data.repository.user.UserCacheDataSource
import phu.nguyen.dateme.data.repository.user.UserRemoteDataSource
import phu.nguyen.dateme.remote.UserRemoteDataSourceImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class UserModule {

    @Binds
    abstract fun bindUserRemoteSource(
        userRemoteDataSourceImpl: UserRemoteDataSourceImpl
    ): UserRemoteDataSource

    @Binds
    abstract fun bindUserCacheDataSource(
        userCacheDataSourceImpl: UserCacheDataSourceImpl
    ): UserCacheDataSource

    @Binds
    abstract fun provideUserRepository(
        userDataRepository: UserRepositoryImpl
    ): UserRepository
}