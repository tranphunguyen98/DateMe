package phu.nguyen.dateme.remote

import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.data.repository.ProfileRemote
import phu.nguyen.dateme.remote.mapper.NetworkProfileMapper

class ProfileRemoteImpl (
    private val profileService: ProfileService,
    private val networkProfileMapper: NetworkProfileMapper
) : ProfileRemote {
    override suspend fun getTopProfiles(): List<Profile> =
        profileService.getTopProfiles().map {
            networkProfileMapper.mapFromRemote(it)
        }
}