package phu.nguyen.dateme.cache

import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.data.repository.user.UserCacheDataSource
import javax.inject.Inject

class UserCacheDataSourceImpl @Inject constructor():
    UserCacheDataSource {

    override suspend fun getUser(uid: String): User {
        TODO("Not yet implemented")
    }

}