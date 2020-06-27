package phu.nguyen.dateme.data.repository.profile

import phu.nguyen.dateme.data.model.SwipeProfile

/**
 * Interface defining methods for the caching of Profile. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface ProfileCacheDataSource {
    suspend fun getMyProfile() : SwipeProfile
    suspend fun saveMyProfile(swipeProfile: SwipeProfile)
}