package phu.nguyen.dateme.cache

import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.data.repository.profile.ProfileCacheDataSource
import javax.inject.Inject

class ProfileCacheDataSourceImpl @Inject constructor():
    ProfileCacheDataSource {
    override suspend fun getMyProfile(): Profile {
        TODO("Not yet implemented")
    }

    override suspend fun saveMyProfile(swipeProfile: Profile) {
        TODO("Not yet implemented")
    }

}