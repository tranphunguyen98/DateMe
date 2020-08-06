package phu.nguyen.dateme.data.source.matching

import phu.nguyen.dateme.data.model.Matching
import phu.nguyen.dateme.data.repository.matching.MatchingDataStore
import javax.inject.Inject

/**
 * Implementation of the [MatchingDataStore] interface to provide a means of communicating
 * with the local data source
 */

class MatchingCacheDataStore @Inject constructor() :
    MatchingDataStore {

    override suspend fun getMatching(): List<Matching> {
        TODO("Not yet implemented")
    }

    override suspend fun saveMatching(matching: Matching) {
        TODO("Not yet implemented")
    }

}