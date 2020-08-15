package phu.nguyen.dateme.data.repository.matching

import phu.nguyen.dateme.data.model.Interaction

/**
 * Interface defining methods for the caching of Profile. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface MatchingCacheDataSource {
    suspend fun getMatching() : List<Interaction>
    suspend fun saveMatching(matching: Interaction)
}