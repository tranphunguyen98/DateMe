package phu.nguyen.dateme.cache

import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.data.repository.user.UserCacheDataSource
import phu.nguyen.dateme.remote.model.NetworkUser
import javax.inject.Inject

class UserCacheDataSourceImpl @Inject constructor():
    UserCacheDataSource {

    override suspend fun getUser(uid: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: NetworkUser) {
        TODO("Not yet implemented")
    }

}