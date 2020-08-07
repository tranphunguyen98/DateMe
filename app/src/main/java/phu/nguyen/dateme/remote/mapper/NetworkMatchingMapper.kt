package phu.nguyen.dateme.remote.mapper

import phu.nguyen.dateme.data.model.Matching
import phu.nguyen.dateme.remote.model.NetworkMatching
import javax.inject.Inject

class NetworkMatchingMapper @Inject constructor() : NetworkMapper<NetworkMatching, Matching> {
    override fun mapFromRemote(type: NetworkMatching): Matching =
        Matching(type.uid,type.typeSwipe, type.match)

    override fun mapToRemote(type: Matching): NetworkMatching =
        NetworkMatching(type.uid,type.typeSwipe, type.match)

}