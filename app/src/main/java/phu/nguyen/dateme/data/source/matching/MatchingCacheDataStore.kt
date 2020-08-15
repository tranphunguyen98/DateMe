package phu.nguyen.dateme.data.source.matching

import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.data.repository.matching.MatchingDataStore
import javax.inject.Inject

/**
 * Implementation of the [MatchingDataStore] interface to provide a means of communicating
 * with the local data source
 */

class MatchingCacheDataStore @Inject constructor() :
    MatchingDataStore {

    override suspend fun getMatching(): List<Interaction> {
        TODO("Not yet implemented")
    }

    override suspend fun saveMatching(matching: Interaction) {
        TODO("Not yet implemented")
    }

    override suspend fun checkMatching(uidSource: String): Boolean {
        TODO("Not yet implemented")
    }

}