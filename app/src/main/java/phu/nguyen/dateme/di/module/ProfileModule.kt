package phu.nguyen.dateme.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import phu.nguyen.dateme.cache.ProfileCacheDataSourceImpl
import phu.nguyen.dateme.data.ProfileRepository
import phu.nguyen.dateme.data.ProfileRepositoryImpl
import phu.nguyen.dateme.data.repository.profile.ProfileCacheDataSource
import phu.nguyen.dateme.data.repository.profile.ProfileRemoteDataSource
import phu.nguyen.dateme.remote.source.profile.ProfileRemoteDataSourceImpl

@Module
@InstallIn(FragmentComponent::class)
abstract class ProfileModule {

    @Binds
    abstract fun bindProfileRemoteSource(
        profileRemoteDataSourceImpl: ProfileRemoteDataSourceImpl
    ): ProfileRemoteDataSource

    @Binds
    abstract fun bindProfileCacheDataSource(
        profileCacheDataSourceImpl: ProfileCacheDataSourceImpl
    ): ProfileCacheDataSource

    @Binds
    abstract fun provideProfileRepository(
        profileDataRepository: ProfileRepositoryImpl
    ): ProfileRepository
}