package phu.nguyen.dateme.data.mapper

import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.remote.model.NetworkInteraction
import javax.inject.Inject

class MatchingMapper @Inject constructor() : UIMapper<Interaction,NetworkInteraction> {
    override fun mapFromUI(type: Interaction): NetworkInteraction {
        return NetworkInteraction(type.uid,type.typeSwipe,type.match)
    }

}