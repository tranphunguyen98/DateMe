package phu.nguyen.dateme.data.mapper

import phu.nguyen.dateme.data.model.Matching
import phu.nguyen.dateme.remote.model.NetworkMatching
import javax.inject.Inject

class MatchingMapper @Inject constructor() : UIMapper<Matching,NetworkMatching> {
    override fun mapFromUI(type: Matching): NetworkMatching {
        return NetworkMatching(type.uid,type.typeSwipe,type.match)
    }

}