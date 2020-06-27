package phu.nguyen.dateme.data.repository.user

import phu.nguyen.dateme.data.model.User

/**
 * Interface defining methods for the data operations related to Profile.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */

interface UserDataStore {
    suspend fun getUser(uid : String) : User
}