package phu.nguyen.dateme.remote.mapper

import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.remote.model.NetworkInteraction
import javax.inject.Inject

class NetworkMatchingMapper @Inject constructor() : NetworkMapper<NetworkInteraction, Interaction> {
    override fun mapFromRemote(type: NetworkInteraction): Interaction =
        Interaction(type.uid,type.interactiveType, type.match)

    override fun mapToRemote(type: Interaction): NetworkInteraction =
        NetworkInteraction(type.uid,type.typeSwipe, type.match)

}