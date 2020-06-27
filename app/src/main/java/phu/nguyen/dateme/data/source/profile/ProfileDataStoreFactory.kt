package phu.nguyen.dateme.data.source.profile

import phu.nguyen.dateme.data.repository.profile.ProfileDataStore
import javax.inject.Inject

/**
 * Create an instance of a ProfileDataStore
 */
class ProfileDataStoreFactory @Inject constructor(
    private val profileCacheDataStore: ProfileCacheDataStore,
    private val profileRemoteDataStore: ProfileRemoteDataStore
) {
    fun retrieveCacheDataStore(): ProfileDataStore {
        return profileCacheDataStore
    }

    fun retrieveRemoteDataStore(): ProfileDataStore {
        return profileRemoteDataStore
    }
}