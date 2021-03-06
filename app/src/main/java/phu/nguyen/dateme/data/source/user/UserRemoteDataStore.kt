package phu.nguyen.dateme.data.source.user

import android.net.Uri
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.data.repository.user.UserDataStore
import phu.nguyen.dateme.data.repository.user.UserRemoteDataSource
import phu.nguyen.dateme.remote.model.NetworkUser
import javax.inject.Inject

class UserRemoteDataStore @Inject constructor(private val userRemote: UserRemoteDataSource) :
    UserDataStore {

    override suspend fun getUser(uid: String): User =
        userRemote.getUser(uid)

    override suspend fun saveUser(user: NetworkUser) {
        userRemote.saveUser(user)
    }

    override suspend fun uploadImage(path: String, uri: Uri): String =
        userRemote.uploadImage(path, uri)

}