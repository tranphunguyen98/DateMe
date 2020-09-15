package phu.nguyen.dateme.remote.mapper

import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.remote.model.NetworkChat
import javax.inject.Inject

class NetworkChatMapper @Inject constructor() : NetworkMapper<NetworkChat, Chat> {
    override fun mapFromRemote(type: NetworkChat): Chat =
        Chat(
            id = type.id,
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
            type.id,
            type.new,
            type.matchingTime,
            type.matchingAvatar,
            type.matchingName,
            type.onlineStatus,
            type.timeOffline,
            type.messages
        )

}