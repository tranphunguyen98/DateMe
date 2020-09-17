package phu.nguyen.dateme.remote.source.chat

import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.data.chat.model.Message
import phu.nguyen.dateme.data.chat.repository.ChatRemoteDataSource
import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.remote.mapper.NetworkChatMapper
import phu.nguyen.dateme.remote.mapper.NetworkMessageMapper
import javax.inject.Inject

class ChatRemoteDataSourceImpl @Inject constructor(
    private val chatService: ChatService,
    private val mapperChat: NetworkChatMapper,
    private val mapperMessage: NetworkMessageMapper
) : ChatRemoteDataSource {
    override suspend fun getChats(): List<Chat> = chatService.getChats().map {
        mapperChat.mapFromRemote(it)
    }

    override suspend fun saveChat(idChat: String, message: Message) {
        chatService.saveChat(idChat, mapperMessage.mapToRemote(message))
    }

    override suspend fun getMoreChat(idChat: String): Chat {
        TODO("Not yet implemented")
    }

    override suspend fun getChat(idChat: String): Chat {
        TODO("Not yet implemented")
    }

    override suspend fun saveFirstChat(interaction: Interaction) {
        chatService.saveFirstChat(interaction)
    }

}