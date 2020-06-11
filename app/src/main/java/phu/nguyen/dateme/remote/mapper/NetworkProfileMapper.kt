package phu.nguyen.dateme.remote.mapper

import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.remote.model.NetworkProfile

class NetworkProfileMapper() : NetworkMapper<NetworkProfile, Profile> {
    override fun mapFromRemote(type: NetworkProfile): Profile {
        return Profile(
            type.id,
            type.name,
            type.age,
            type.introduction,
            type.images,
            type.latitude,
            type.longitude
        )
    }

}