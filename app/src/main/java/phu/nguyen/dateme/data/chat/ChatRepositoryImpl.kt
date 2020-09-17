package phu.nguyen.dateme.data.chat

import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.data.chat.model.Message
import phu.nguyen.dateme.data.chat.source.ChatDataStoreFactory
import phu.nguyen.dateme.data.model.Interaction
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val factory: ChatDataStoreFactory
) : ChatRepository {
    override suspend fun getChats(): List<Chat> = factory.retrieveRemoteDataStore().getChats()
    override suspend fun saveChat(idChat: String, message: Message) =
        factory.retrieveRemoteDataStore().saveChat(idChat, message)

    override suspend fun getMoreChat(idChat: String): Chat =
        factory.retrieveRemoteDataStore().getMoreChat(idChat)

    override suspend fun getChat(idChat: String): Chat =
        factory.retrieveRemoteDataStore().getChat(idChat)

    override suspend fun saveFirstChat(interaction: Interaction) {
        factory.retrieveRemoteDataStore().saveFirstChat(interaction)
    }

}