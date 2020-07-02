package phu.nguyen.dateme.data.repository.user

import android.net.Uri
import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.remote.model.NetworkUser

/**
 * Interface defining methods for the data operations related to Profile.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */

interface UserDataStore {
    suspend fun getUser(uid : String) : User
    suspend fun saveUser(user: NetworkUser)
    suspend fun uploadImage(path: String, uri: Uri): String
}