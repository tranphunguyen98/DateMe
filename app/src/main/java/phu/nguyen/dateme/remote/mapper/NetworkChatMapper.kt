package phu.nguyen.dateme.remote.mapper

import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.remote.model.NetworkChat
import javax.inject.Inject

class NetworkChatMapper @Inject constructor() : NetworkMapper<NetworkChat, Chat> {
    override fun mapFromRemote(type: NetworkChat): Chat =
        Chat(
            id = type.id,
            ids = type.ids,
            new = type.new,
            matchingTime = type.matchingTime,
            matchingAvatar = type.matchingAvatar,
            matchingName = type.matchingName,
            onlineStatus = type.onlineStatus,
            timeOffline = type.timeOffline,
            messages = type.messages
        )

    override fun mapToRemote(type: Chat): NetworkChat =
        NetworkChat(
            id = type.id,
            ids = type.ids,
            new = type.new,
            matchingTime = type.matchingTime,
            matchingAvatar = type.matchingAvatar,
            matchingName = type.matchingName,
            onlineStatus = type.onlineStatus,
            timeOffline = type.timeOffline,
            messages = type.messages
        )

}