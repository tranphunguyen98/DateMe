package phu.nguyen.dateme.remote

import phu.nguyen.dateme.data.model.SwipeProfile
import phu.nguyen.dateme.data.repository.profile.ProfileRemoteDataSource
import phu.nguyen.dateme.remote.mapper.NetworkProfileMapper
import javax.inject.Inject

class ProfileRemoteDataSourceImpl @Inject constructor(
    private val profileService: ProfileService,
    private val networkProfileMapper: NetworkProfileMapper
) : ProfileRemoteDataSource {
    override suspend fun getTopProfiles(): List<SwipeProfile> =
        profileService.getTopProfiles().map {
            networkProfileMapper.mapFromRemote(it)
        }
}