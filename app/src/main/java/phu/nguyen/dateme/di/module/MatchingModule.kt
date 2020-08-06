package phu.nguyen.dateme.di.module

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import phu.nguyen.dateme.cache.MatchingCacheDataSourceImpl
import phu.nguyen.dateme.data.MatchingRepository
import phu.nguyen.dateme.data.MatchingRepositoryImpl
import phu.nguyen.dateme.data.repository.matching.MatchingCacheDataSource
import phu.nguyen.dateme.data.repository.matching.MatchingRemoteDataSource
import phu.nguyen.dateme.remote.source.matching.MatchingRemoteDataSourceImpl

@Module
@InstallIn(FragmentComponent::class)
abstract class MatchingModule {

    @Binds
    abstract fun bindMatchingRemoteSource(
        profileRemoteDataSourceImpl: MatchingRemoteDataSourceImpl
    ): MatchingRemoteDataSource

    @Binds
    abstract fun bindMatchingCacheDataSource(
        profileCacheDataSourceImpl: MatchingCacheDataSourceImpl
    ): MatchingCacheDataSource

    @Binds
    abstract fun provideMatchingRepository(
        profileDataRepository: MatchingRepositoryImpl
    ): MatchingRepository
}