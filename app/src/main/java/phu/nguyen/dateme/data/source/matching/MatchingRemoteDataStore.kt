package phu.nguyen.dateme.data.source.matching

import phu.nguyen.dateme.data.model.Matching
import phu.nguyen.dateme.data.repository.matching.MatchingDataStore
import phu.nguyen.dateme.data.repository.matching.MatchingRemoteDataSource
import javax.inject.Inject

 class MatchingRemoteDataStore @Inject constructor(private val matchingRemote: MatchingRemoteDataSource) :
    MatchingDataStore {

    override suspend fun getMatching(): List<Matching> = matchingRemote.getMatching()

    override suspend fun saveMatching( matching: Matching) =
        matchingRemote.saveMatching(matching)

}