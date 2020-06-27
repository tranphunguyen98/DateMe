package phu.nguyen.dateme.data

import android.util.Log
import phu.nguyen.dateme.data.model.SwipeProfile
import phu.nguyen.dateme.data.source.profile.ProfileDataStoreFactory
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val factory: ProfileDataStoreFactory
): ProfileRepository {
    override suspend fun saveMyProfile(swipeProfile: SwipeProfile) = factory.retrieveCacheDataStore().saveMyProfile(swipeProfile)

    override suspend fun getMyProfile(): SwipeProfile = factory.retrieveCacheDataStore().getMyProfile()

    override suspend fun getProfiles(): List<SwipeProfile>  {
        Log.d("testCoroutine", Thread.currentThread().name)
        Log.d("testRemote", "ProfileRepository")
        return factory.retrieveRemoteDataStore().getTopProfiles()
    }

}