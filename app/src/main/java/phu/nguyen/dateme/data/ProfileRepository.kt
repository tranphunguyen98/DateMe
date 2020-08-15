package phu.nguyen.dateme.data

import phu.nguyen.dateme.data.model.Profile

interface ProfileRepository {
    suspend fun saveMyProfile(swipeProfile: Profile)
    suspend fun getMyProfile(): Profile
    suspend fun getProfiles(): List<Profile>
    suspend fun getInteractiveProfiles(interactiveType: Int): List<Profile>
}