package phu.nguyen.dateme.data.repository.matching

import phu.nguyen.dateme.data.model.Matching

/**
 * Interface defining methods for the data operations related to Matching.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */

interface MatchingDataStore {
    suspend fun getMatching() : List<Matching>
    suspend fun saveMatching(matching: Matching)
    suspend fun checkMatching(uidSource: String): Boolean
}