package phu.nguyen.dateme.data

import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.data.source.matching.MatchingDataStoreFactory
import javax.inject.Inject

class MatchingRepositoryImpl @Inject constructor(
    private val factory: MatchingDataStoreFactory
) : MatchingRepository {

    override suspend fun getMatching(): List<Interaction> =
        factory.retrieveRemoteDataStore().getMatching()

    override suspend fun saveMatching(matching: Interaction) =
        factory.retrieveRemoteDataStore().saveMatching(matching)

    override suspend fun checkMatching(uidSource: String): Boolean =
        factory.retrieveRemoteDataStore().checkMatching(uidSource)

}