package phu.nguyen.dateme.data

import phu.nguyen.dateme.data.model.User

interface UserRepository {
    suspend fun getUser(uid: String) : User
}