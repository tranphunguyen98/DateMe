package phu.nguyen.dateme.data.source

import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.data.repository.ProfileDataStore

/**
 * Implementation of the [ProfileDataStore] interface to provide a means of communicating
 * with the local data source
 */

class ProfileCacheDataStore() : ProfileDataStore {
    override suspend fun saveMyProfile(profile: Profile) {
        TODO("Not yet implemented")
    }

    override suspend fun getMyProfile(): Profile {
        TODO("Not yet implemented")
    }

    override suspend fun getTopProfiles(): List<Profile> {
        throw UnsupportedOperationException()
    }
}