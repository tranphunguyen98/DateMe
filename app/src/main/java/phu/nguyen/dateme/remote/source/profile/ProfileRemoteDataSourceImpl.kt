package phu.nguyen.dateme.remote.source.profile

import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.data.repository.profile.ProfileRemoteDataSource
import phu.nguyen.dateme.remote.mapper.NetworkProfileMapper
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val profileService: ProfileService,
    private val networkProfileMapper: NetworkProfileMapper
) : ProfileRemoteDataSource {
    override suspend fun getTopProfiles(): List<Profile> =
        profileService.getTopProfiles().map {
            networkProfileMapper.mapFromRemote(it)
        }

    override suspend fun getInteractiveProfiles(interactiveType: Int): List<Profile> =
        profileService.getInteractiveProfiles(interactiveType).map {
            networkProfileMapper.mapFromRemote(it)
        }
}