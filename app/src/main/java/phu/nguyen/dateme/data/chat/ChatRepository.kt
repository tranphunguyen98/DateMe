package phu.nguyen.dateme.data.chat

import phu.nguyen.dateme.data.chat.model.Chat
import phu.nguyen.dateme.data.chat.model.Message
import phu.nguyen.dateme.data.model.Interaction

interface ChatRepository {
    suspend fun getChats() : List<Chat>
    suspend fun saveChat(idChat: String, message: Message)
    suspend fun getMoreChat(idChat: String): Chat
    suspend fun getChat(idChat: String): Chat
    suspend fun saveFirstChat(interaction: Interaction)
}