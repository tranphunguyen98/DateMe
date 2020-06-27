package phu.nguyen.dateme.data

import phu.nguyen.dateme.data.model.SwipeProfile

interface ProfileRepository {
    suspend fun saveMyProfile(swipeProfile: SwipeProfile)
    suspend fun getMyProfile(): SwipeProfile
    suspend fun getProfiles(): List<SwipeProfile>
}