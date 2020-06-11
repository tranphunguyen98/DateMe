package phu.nguyen.dateme.data.source

import phu.nguyen.dateme.data.repository.ProfileDataStore

/**
 * Create an instance of a ProfileDataStore
 */
class ProfileDataStoreFactory(
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