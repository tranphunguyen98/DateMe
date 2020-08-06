package phu.nguyen.dateme.data.source.matching

import phu.nguyen.dateme.data.repository.matching.MatchingDataStore
import javax.inject.Inject

/**
 * Create an instance of a MatchingDataStore
 */
class MatchingDataStoreFactory @Inject constructor(
    private val matchingCacheDataStore: MatchingCacheDataStore,
    private val matchingRemoteDataStore: MatchingRemoteDataStore
) {
    fun retrieveCacheDataStore(): MatchingDataStore {
        return matchingCacheDataStore
    }

    fun retrieveRemoteDataStore(): MatchingDataStore {
        return matchingRemoteDataStore
    }
}