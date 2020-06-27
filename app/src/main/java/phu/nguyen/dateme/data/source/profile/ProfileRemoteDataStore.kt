package phu.nguyen.dateme.data.source.profile

import phu.nguyen.dateme.data.model.SwipeProfile
import phu.nguyen.dateme.data.repository.profile.ProfileDataStore
import phu.nguyen.dateme.data.repository.profile.ProfileRemoteDataSource
import javax.inject.Inject

class ProfileRemoteDataStore @Inject constructor(private val profileRemote: ProfileRemoteDataSource) :
    ProfileDataStore {
    override suspend fun saveMyProfile(swipeProfile: SwipeProfile) {
        TODO("Not yet implemented")
    }

    override suspend fun getMyProfile(): SwipeProfile {
        TODO("Not yet implemented")
    }

    override suspend fun getTopProfiles(): List<SwipeProfile> =
       profileRemote.getTopProfiles()
}