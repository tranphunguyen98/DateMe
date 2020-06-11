package phu.nguyen.dateme.data

import phu.nguyen.dateme.data.model.Profile

interface ProfileRepository {
    suspend fun saveMyProfile(profile: Profile)
    suspend fun getMyProfile(): Profile
    suspend fun getProfiles(): List<Profile>
}