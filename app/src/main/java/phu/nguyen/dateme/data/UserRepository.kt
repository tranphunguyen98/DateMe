package phu.nguyen.dateme.data

import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.remote.model.NetworkUser

interface UserRepository {
    suspend fun getUser(uid: String) : User
    suspend fun saveUser(user: NetworkUser)
}