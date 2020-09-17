package phu.nguyen.dateme.cache

import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.data.chat.model.Message
import phu.nguyen.dateme.data.chat.repository.ChatCacheDataSource
import javax.inject.Inject

class ChatCacheDataSourceImpl @Inject constructor():
    ChatCacheDataSource {
    override suspend fun getChats(): List<Chat> {
        TODO("Not yet implemented")
    }

    override suspend fun saveChat(idChat: String, message: Message) {
        TODO("Not yet implemented")
    }


    override suspend fun getMoreChat(idChat: String): Chat {
        TODO("Not yet implemented")
    }

    override suspend fun getChat(idChat: String): Chat {
        TODO("Not yet implemented")
    }

}