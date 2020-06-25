package phu.nguyen.dateme.data.source

import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.data.repository.profile.ProfileDataStore
import phu.nguyen.dateme.data.repository.profile.ProfileRemoteDataSource
import javax.inject.Inject

class ProfileRemoteDataStore @Inject constructor(private val profileRemote: ProfileRemoteDataSource) :
    ProfileDataStore {
    override suspend fun saveMyProfile(profile: Profile) {
        TODO("Not yet implemented")
    }

    override suspend fun getMyProfile(): Profile {
        TODO("Not yet implemented")
    }

    override suspend fun getTopProfiles(): List<Profile> =
       profileRemote.getTopProfiles()
}