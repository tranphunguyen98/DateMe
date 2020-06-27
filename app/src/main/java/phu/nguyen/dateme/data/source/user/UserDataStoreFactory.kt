package phu.nguyen.dateme.data.source.user

import phu.nguyen.dateme.data.repository.user.UserDataStore
import javax.inject.Inject

/**
 * Create an instance of a UserDataStore
 */
class UserDataStoreFactory @Inject constructor(
    private val userCacheDataStore: UserCacheDataStore,
    private val userRemoteDataStore: UserRemoteDataStore
) {
    fun retrieveCacheDataStore(): UserDataStore {
        return userCacheDataStore
    }

    fun retrieveRemoteDataStore(): UserDataStore {
        return userRemoteDataStore
    }
}