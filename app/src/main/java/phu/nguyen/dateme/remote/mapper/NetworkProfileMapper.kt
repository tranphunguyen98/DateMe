package phu.nguyen.dateme.remote.mapper

import phu.nguyen.dateme.data.model.SwipeProfile
import phu.nguyen.dateme.remote.model.NetworkProfile
import javax.inject.Inject

class NetworkProfileMapper @Inject constructor() : NetworkMapper<NetworkProfile, SwipeProfile> {
    override fun mapFromRemote(type: NetworkProfile): SwipeProfile = SwipeProfile(
        type.id,
        type.name + ",",
        type.age.toString(),
        type.introduction,
        type.images,
        type.latitude,
        type.longitude
    )


}