package phu.nguyen.dateme.data.repository.user

import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.remote.model.NetworkUser

/**
 * Interface defining methods for the remoting of Profile. This is to be implemented by the
 * remote layer, using this interface as a way of communicating.
 */

interface UserRemoteDataSource {
    suspend fun getUser(uid: String) : User
    suspend fun saveUser(user: NetworkUser)
}