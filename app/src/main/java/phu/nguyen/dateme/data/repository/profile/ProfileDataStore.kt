package phu.nguyen.dateme.data.repository.profile

import phu.nguyen.dateme.data.model.Profile

/**
 * Interface defining methods for the data operations related to Profile.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */
interface ProfileDataStore {
    suspend fun saveMyProfile(profile: Profile)
    suspend fun getMyProfile(): Profile
    suspend fun getTopProfiles() : List<Profile>
}