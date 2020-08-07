package phu.nguyen.dateme.data

import phu.nguyen.dateme.data.model.Matching

interface MatchingRepository {
    suspend fun getMatching(): List<Matching>
    suspend fun saveMatching(matching: Matching)
    suspend fun checkMatching(uidSource: String): Boolean
}