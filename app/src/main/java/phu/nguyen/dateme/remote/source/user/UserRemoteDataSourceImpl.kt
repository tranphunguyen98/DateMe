package phu.nguyen.dateme.remote.source.user

import android.net.Uri
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.data.repository.user.UserRemoteDataSource
import phu.nguyen.dateme.remote.mapper.NetworkUserMapper
import phu.nguyen.dateme.remote.model.NetworkUser
import javax.inject.Inject

class UserRemoteDataSourceImpl @Inject constructor(
    private val userService: UserService,
    private val networkUserMapper: NetworkUserMapper
) : UserRemoteDataSource {

    override suspend fun getUser(uid: String): User =
       networkUserMapper.mapFromRemote(userService.getUser(uid))

    override suspend fun saveUser(user: NetworkUser) {
        userService.saveUser(user)
    }

    override suspend fun uploadImage(path: String, uri: Uri): String = userService.saveImage(path,uri)

}