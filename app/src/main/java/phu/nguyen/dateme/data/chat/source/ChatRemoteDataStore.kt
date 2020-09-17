package phu.nguyen.dateme.data.chat.source

import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.data.chat.model.Message
import phu.nguyen.dateme.data.chat.repository.ChatDataStore
import phu.nguyen.dateme.data.chat.repository.ChatRemoteDataSource
import phu.nguyen.dateme.data.model.Interaction
import javax.inject.Inject

class ChatRemoteDataStore @Inject constructor(private val chatRemoteDataSource: ChatRemoteDataSource) :
    ChatDataStore {
    override suspend fun getChats(): List<Chat> =
        chatRemoteDataSource.getChats()

    override suspend fun saveChat(idChat: String, message: Message) =
        chatRemoteDataSource.saveChat(idChat, message)

    override suspend fun getMoreChat(idChat: String): Chat =
        chatRemoteDataSource.getMoreChat(idChat)

    override suspend fun getChat(idChat: String): Chat = chatRemoteDataSource.getChat(idChat)

    override suspend fun saveFirstChat(interaction: Interaction) {
        chatRemoteDataSource.saveFirstChat(interaction)
    }

}