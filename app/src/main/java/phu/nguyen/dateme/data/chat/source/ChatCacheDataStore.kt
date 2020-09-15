package phu.nguyen.dateme.data.chat.source

import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.data.chat.repository.ChatDataStore
import phu.nguyen.dateme.data.model.Interaction
import javax.inject.Inject

/**
 * Implementation of the [ChatDataStore] interface to provide a means of communicating
 * with the local data source
 */

class ChatCacheDataStore @Inject constructor() :
    ChatDataStore {
    override suspend fun getChats(): List<Chat> {
        TODO("Not yet implemented")
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
        TODO("Not yet implemented")
    }

}