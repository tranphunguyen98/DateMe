package phu.nguyen.dateme.data

import android.net.Uri
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.remote.model.NetworkUser

interface UserRepository {
    suspend fun getUser(uid: String) : User
    suspend fun saveUser(user: NetworkUser)
    suspend fun uploadImage(path: String, uri: Uri): String
}