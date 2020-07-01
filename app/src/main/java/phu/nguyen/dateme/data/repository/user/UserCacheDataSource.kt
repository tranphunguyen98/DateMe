package phu.nguyen.dateme.data.repository.user

import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.remote.model.NetworkUser

/**
 * Interface defining methods for the caching of Profile. This is to be implemented by the
 * cache layer, using this interface as a way of communicating.
 */

interface UserCacheDataSource {
    suspend fun getUser(uid : String) : User
    suspend fun saveUser(user: NetworkUser)
}