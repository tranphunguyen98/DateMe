package phu.nguyen.dateme.remote.mapper

import phu.nguyen.dateme.data.model.User
import phu.nguyen.dateme.remote.model.NetworkUser
import javax.inject.Inject

class NetworkUserMapper @Inject constructor() : NetworkMapper<NetworkUser, User> {
    override fun mapFromRemote(type: NetworkUser): User = User(type.name)
}