package phu.nguyen.dateme.data

import android.util.Log
import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.data.source.ProfileDataStoreFactory
import javax.inject.Inject

class ProfileDataRepository @Inject constructor(
    private val factory: ProfileDataStoreFactory
): ProfileRepository {
    override suspend fun saveMyProfile(profile: Profile) = factory.retrieveCacheDataStore().saveMyProfile(profile)

    override suspend fun getMyProfile(): Profile = factory.retrieveCacheDataStore().getMyProfile()

    override suspend fun getProfiles(): List<Profile>  {
        Log.d("testCoroutine", Thread.currentThread().name)
        Log.d("testRemote", "ProfileRepository")
        return factory.retrieveRemoteDataStore().getTopProfiles()
    }

}