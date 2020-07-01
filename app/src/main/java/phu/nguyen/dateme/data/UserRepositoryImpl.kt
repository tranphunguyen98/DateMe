package phu.nguyen.dateme.data

import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.data.source.user.UserDataStoreFactory
import phu.nguyen.dateme.remote.model.NetworkUser
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val factory: UserDataStoreFactory
) : UserRepository {

    override suspend fun getUser(uid: String): User =
        factory.retrieveRemoteDataStore().getUser(uid)

    override suspend fun saveUser(user: NetworkUser) {
        factory.retrieveRemoteDataStore().saveUser(user)
    }
}