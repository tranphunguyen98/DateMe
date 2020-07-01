package phu.nguyen.dateme.data.source.user
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.data.repository.user.UserDataStore
import phu.nguyen.dateme.remote.model.NetworkUser
import javax.inject.Inject

/**
 * Implementation of the [UserDataStore] interface to provide a means of communicating
 * with the local data source
 */

class UserCacheDataStore @Inject constructor() :
    UserDataStore {

    override suspend fun getUser(uid : String): User {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: NetworkUser) {
        TODO("Not yet implemented")
    }
}