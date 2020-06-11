package phu.nguyen.dateme.data.source

import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.data.repository.ProfileDataStore
import phu.nguyen.dateme.data.repository.ProfileRemote

class ProfileRemoteDataStore(private val profileRemote: ProfileRemote) : ProfileDataStore {
    override suspend fun saveMyProfile(profile: Profile) {
        TODO("Not yet implemented")
    }

    override suspend fun getMyProfile(): Profile {
        TODO("Not yet implemented")
    }

    override suspend fun getTopProfiles(): List<Profile> =
       profileRemote.getTopProfiles()

}