package phu.nguyen.dateme.data.repository.matching

import phu.nguyen.dateme.data.model.Interaction


/**
 * Interface defining methods for the remoting of Matching. This is to be implemented by the
 * remote layer, using this interface as a way of communicating.
 */

interface MatchingRemoteDataSource {
    suspend fun getMatching() : List<Interaction>
    suspend fun saveMatching(matching: Interaction)
    suspend fun checkMatching(uidSource: String): Boolean
}