package phu.nguyen.dateme.cache

import phu.nguyen.dateme.data.model.SwipeProfile
import phu.nguyen.dateme.data.repository.profile.ProfileCacheDataSource
import javax.inject.Inject

class ProfileCacheDataSourceImpl @Inject constructor():
    ProfileCacheDataSource {
    override suspend fun getMyProfile(): SwipeProfile {
        TODO("Not yet implemented")
    }

    override suspend fun saveMyProfile(swipeProfile: SwipeProfile) {
        TODO("Not yet implemented")
    }

}