package phu.nguyen.dateme.remote.source.matching

import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.data.repository.matching.MatchingRemoteDataSource
import phu.nguyen.dateme.remote.mapper.NetworkMatchingMapper
import javax.inject.Inject

class MatchingRemoteDataSourceImpl @Inject constructor(
    private val matchingService: MatchingService,
    private val matchingMapper: NetworkMatchingMapper
) : MatchingRemoteDataSource {

    override suspend fun getMatching(): List<Interaction> {
        TODO("Not yet implemented")
    }

    override suspend fun saveMatching(matching: Interaction) =
        matchingService.saveMatching(matchingMapper.mapToRemote(matching))


    override suspend fun checkMatching(uidSource: String): Boolean =
        matchingService.checkAndSaveMatching(uidSource)

}