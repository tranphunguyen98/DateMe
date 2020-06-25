package phu.nguyen.dateme.data.repository.profile

import phu.nguyen.dateme.data.model.Profile

/**
 * Interface defining methods for the remoting of Profile. This is to be implemented by the
 * remote layer, using this interface as a way of communicating.
 */

interface ProfileRemoteDataSource {
    suspend fun getTopProfiles() : List<Profile>
}