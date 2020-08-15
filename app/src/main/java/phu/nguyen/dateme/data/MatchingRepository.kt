package phu.nguyen.dateme.data

import phu.nguyen.dateme.data.model.Interaction

interface MatchingRepository {
    suspend fun getMatching(): List<Interaction>
    suspend fun saveMatching(matching: Interaction)
    suspend fun checkMatching(uidSource: String): Boolean
}