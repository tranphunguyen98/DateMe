package phu.nguyen.dateme.data

import phu.nguyen.dateme.data.model.Matching
import phu.nguyen.dateme.data.source.matching.MatchingDataStoreFactory
import javax.inject.Inject

class MatchingRepositoryImpl @Inject constructor(
    private val factory: MatchingDataStoreFactory
) : MatchingRepository {

    override suspend fun getMatching(): List<Matching> =
        factory.retrieveRemoteDataStore().getMatching()


    override suspend fun saveMatching(matching: Matching) =
        factory.retrieveRemoteDataStore().saveMatching(matching)

}