package phu.nguyen.dateme.data.source.matching

import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.data.repository.matching.MatchingDataStore
import phu.nguyen.dateme.data.repository.matching.MatchingRemoteDataSource
import javax.inject.Inject

 class MatchingRemoteDataStore @Inject constructor(private val matchingRemote: MatchingRemoteDataSource) :
    MatchingDataStore {

    override suspend fun getMatching(): List<Interaction> = matchingRemote.getMatching()

    override suspend fun saveMatching( matching: Interaction) =
        matchingRemote.saveMatching(matching)

     override suspend fun checkMatching(uidSource: String): Boolean =
         matchingRemote.checkMatching(uidSource)

 }