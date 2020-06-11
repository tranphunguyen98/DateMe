package phu.nguyen.dateme.data.repository

import phu.nguyen.dateme.data.model.Profile

/**
 * Interface defining methods for the caching of Profile. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */
interface ProfileCacheDataSource {
    suspend fun getMyProfile() : Profile
    suspend fun saveMyProfile(profile: Profile)
}