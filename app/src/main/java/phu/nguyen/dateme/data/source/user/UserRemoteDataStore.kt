package phu.nguyen.dateme.data.source.user

import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.data.repository.user.UserDataStore
import phu.nguyen.dateme.data.repository.user.UserRemoteDataSource
import javax.inject.Inject

class UserRemoteDataStore @Inject constructor(private val userRemote: UserRemoteDataSource) :
    UserDataStore {

    override suspend fun getUser(uid: String): User =
        userRemote.getUser(uid)

}