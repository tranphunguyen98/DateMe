package phu.nguyen.dateme.remote.source.chat

import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.data.chat.repository.ChatRemoteDataSource
import phu.nguyen.dateme.data.model.Interaction
import phu.nguyen.dateme.remote.mapper.NetworkChatMapper
import javax.inject.Inject

class ChatRemoteDataSourceImpl @Inject constructor(
    private val chatService: ChatService,
    private val mapper: NetworkChatMapper
) : ChatRemoteDataSource {
    override suspend fun getChats(): List<Chat> = chatService.getChats().map {
        mapper.mapFromRemote(it)
    }

    override suspend fun saveChat(chat: Chat) {
        TODO("Not yet implemented")
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