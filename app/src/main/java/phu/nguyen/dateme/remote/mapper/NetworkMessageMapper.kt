package phu.nguyen.dateme.remote.mapper

import phu.nguyen.dateme.data.chat.model.Message
import phu.nguyen.dateme.remote.model.NetworkMessage
import javax.inject.Inject

class NetworkMessageMapper @Inject constructor() : NetworkMapper<NetworkMessage, Message> {
    override fun mapFromRemote(type: NetworkMessage): Message =
        Message(
            id = type.id,
            content = type.content,
            contentType = type.contentType,
            mediaThumpUrl = type.mediaThumpUrl,
            mediaUrl = type.mediaUrl,
            senderId = type.senderId,
            status = type.status,
            timestamp = type.timestamp
        )

    override fun mapToRemote(type: Message): NetworkMessage =
        NetworkMessage(
            id = type.id,
            content = type.content,
            contentType = type.contentType,
            mediaThumpUrl = type.mediaThumpUrl,
            mediaUrl = type.mediaUrl,
            senderId = type.senderId,
            status = type.status,
            timestamp = type.timestamp
        )

}