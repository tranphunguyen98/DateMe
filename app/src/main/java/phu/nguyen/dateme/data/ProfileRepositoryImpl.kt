package phu.nguyen.dateme.data

import phu.nguyen.dateme.data.model.Profile
import phu.nguyen.dateme.data.source.profile.ProfileDataStoreFactory
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val factory: ProfileDataStoreFactory
) : ProfileRepository {
    override suspend fun saveMyProfile(swipeProfile: Profile) =
        factory.retrieveCacheDataStore().saveMyProfile(swipeProfile)

    override suspend fun getMyProfile(): Profile = factory.retrieveCacheDataStore().getMyProfile()

    override suspend fun getProfiles(): List<Profile> =
        factory.retrieveRemoteDataStore().getTopProfiles()


    override suspend fun getInteractiveProfiles(interactiveType: Int): List<Profile> =
        factory.retrieveRemoteDataStore().getLikeYouProfiles(interactiveType)

}