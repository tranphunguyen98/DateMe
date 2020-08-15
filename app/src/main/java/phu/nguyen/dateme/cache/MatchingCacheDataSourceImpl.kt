package phu.nguyen.dateme.cache

import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.data.repository.matching.MatchingCacheDataSource
import javax.inject.Inject

class MatchingCacheDataSourceImpl @Inject constructor():
    MatchingCacheDataSource {

    override suspend fun getMatching(): List<Interaction> {
        TODO("Not yet implemented")
    }

    override suspend fun saveMatching(matching: Interaction) {
        TODO("Not yet implemented")
    }

}