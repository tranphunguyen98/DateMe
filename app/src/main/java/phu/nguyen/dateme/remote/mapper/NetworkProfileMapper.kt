package phu.nguyen.dateme.remote.mapper

import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.remote.model.NetworkProfile
import javax.inject.Inject

class NetworkProfileMapper @Inject constructor() : NetworkMapper<NetworkProfile, Profile> {
    override fun mapFromRemote(type: NetworkProfile): Profile = Profile(
        type.id,
        type.name + ",",
        type.age.toString(),
        type.introduction,
        type.images,
        type.latitude,
        type.longitude
    )


}