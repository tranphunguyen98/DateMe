package phu.nguyen.dateme.data.source.profile

import phu.nguyen.dateme.data.model.SwipeProfile
import phu.nguyen.dateme.data.repository.profile.ProfileDataStore
import javax.inject.Inject

/**
 * Implementation of the [ProfileDataStore] interface to provide a means of communicating
 * with the local data source
 */

class ProfileCacheDataStore @Inject constructor() :
    ProfileDataStore {
    override suspend fun saveMyProfile(swipeProfile: SwipeProfile) {
        TODO("Not yet implemented")
    }

    override suspend fun getMyProfile(): SwipeProfile {
        TODO("Not yet implemented")
    }

    override suspend fun getTopProfiles(): List<SwipeProfile> {
        throw UnsupportedOperationException()
    }
}