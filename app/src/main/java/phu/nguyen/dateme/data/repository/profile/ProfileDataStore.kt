package phu.nguyen.dateme.data.repository.profile

import phu.nguyen.dateme.data.model.SwipeProfile

/**
 * Interface defining methods for the data operations related to Profile.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface ProfileDataStore {
    suspend fun saveMyProfile(swipeProfile: SwipeProfile)
    suspend fun getMyProfile(): SwipeProfile
    suspend fun getTopProfiles() : List<SwipeProfile>
}